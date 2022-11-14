package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Film;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListByCinemaIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmStandardVO;
import cn.tedu.galaxy.film.mapper.FilmMapper;
import cn.tedu.galaxy.film.mapper.FilmTypeMapper;
import cn.tedu.galaxy.film.service.IFilmService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService
@Slf4j
@Service
public class FilmServiceImpl implements IFilmService {

    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private FilmTypeMapper filmTypeMapper;

    public FilmServiceImpl() {
        log.info("创建业务对象：FilmServiceImpl");
    }

    @Override
    public void save(FilmAddNewDTO filmAddNewDTO) {
        log.debug("开始处理【添加电影】的业务，参数：{}", filmAddNewDTO);
        String name = filmAddNewDTO.getName();
        int countByName = filmMapper.countByName(name);
        // 判断统计结果是否大于0
        if (countByName > 0) {
            String message = "添加电影失败！电影名称【" + name + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Film film = new Film();
        BeanUtils.copyProperties(filmAddNewDTO, film);
        log.debug("即将向数据库中插入数据：{}", film);
        int rows = filmMapper.insert(film);
        if (rows != 1) {
            String message = "添加电影失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除电影】的业务，参数：{}", id);
        FilmStandardVO queryResult = filmMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "删除电影失败，尝试访问的数据不存在！";
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 执行删除
        log.debug("即使删除id为{}的属性……", id);
        int rows = filmMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除属性失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
        log.debug("删除完成！");
    }

    @Override
    public void updateById(FilmUpdateDTO filmUpdateDTO) {
        log.debug("开始处理【修改电影】的业务，新基本电影={}", filmUpdateDTO);
        FilmStandardVO queryResult = filmMapper.getStandardById(filmUpdateDTO.getId());
        // 判断查询结果是否为null
        if (queryResult == null) {
            // 抛出ServiceException，业务状态码：40400
            String message = "修改电影基本资料失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        // 创建Film对象，将作为修改时的参数
        Film film = new Film();
        BeanUtils.copyProperties(filmUpdateDTO, film);
        // 调用Mapper对象的update()修改属性基本资料，并获取返回值
        log.debug("即将修改电影：{}", film);
        int rows = filmMapper.updateById(film);
        // 判断返回值是否不等于1
        if (rows != 1) {
            // 是：抛出ServiceException（ERR_INSERT）
            String message = "修改电影，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }


    @Override
    public List<FilmListItemVO> list() {
        log.debug("开始处理【查询电影列表】的业务……");
        return filmMapper.list();
    }

    @Override
    public List<FilmListItemVO> listBy(String type, String region, String year) {
        log.debug("开始处理【根据条件查询电影列表】的业务……");
        log.debug("{}--{}--{}",type,region,year);
        if(type.equals("全部")){
            type="";
        }
        if(region.equals("全部")){
            region="";
        }
        if(year.equals("全部")){
            year="";
        }
        log.debug("{}--{}--{}",type,region,year);
        List<FilmListItemVO> result = filmMapper.listByTypeRegionYear(type, region, year);
        return result;
    }

    @Override
    public List<FilmListItemVO> findHots() {
        List<FilmListItemVO> hots = filmMapper.findHots();
        return hots;
    }

    @Override
    public Film findFilmById(Long id) {
        log.debug("【根据电影id查询电影】业务");
        return filmMapper.findFilmById(id);
    }

    @Override
    public List<FilmListItemVO> findAllFilm(int state) {
        log.debug("【根据电影状态查询所有电影列表】业务");
        return filmMapper.findAllFilm(state);
    }

//    @Override
//    public List<FilmListItemVO> findFilmLikeName(String name) {
//        log.debug("【根据电影名查询相关电影列表】业务");
//        return filmMapper.findFilmLikeName(name);
//    }

//    @Override
//    public List<FilmListItemVO> findFilmLikeType(String type) {
//        log.debug("【根据电影类型查询电影列表】业务");
//        return filmMapper.findFilmLikeType(type);
//    }

    @Override
    public List<FilmListByCinemaIdVO> listByCinemaId(Long cinemaId) {
        log.debug("【根据影院id查询电影列表】业务");
        return filmMapper.listByCinemaId(cinemaId);
    }




}
