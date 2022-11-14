package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.CinemaAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.CinemaUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Cinema;
import cn.tedu.galaxy.commons.pojo.film.vo.*;
import cn.tedu.galaxy.film.mapper.CinemaBrandMapper;
import cn.tedu.galaxy.film.mapper.CinemaMapper;
import cn.tedu.galaxy.film.mapper.ScreenMapper;
import cn.tedu.galaxy.film.mapper.ScreenTypeMapper;
import cn.tedu.galaxy.film.service.ICinemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CinemaServiceImpl implements ICinemaService {
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private CinemaBrandMapper cinemaBrandMapper;
    @Autowired
    private ScreenTypeMapper screenTypeMapper;
    @Autowired
    private ScreenMapper screenMapper;

    @Override
    public void save(CinemaAddNewDTO cinemaAddNewDTO) {
        int countByName = cinemaMapper.countByName(cinemaAddNewDTO.getName());
        if (countByName != 0) {
            String message = "添加影院失败！影院【" + cinemaAddNewDTO.getName() + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
//
//        CinemaBrandStandardVO cinemaBrand = cinemaBrandMapper.getStandardById(cinemaAddNewDTO.getBrandId());
//        if (cinemaBrand == null) {
//            String message = "添加影院失败！影院品牌【" + cinemaAddNewDTO.getBrand() + "】不存在！";
//            log.warn(message);
//            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
//        }
//
//        ScreenTypeStandardVO screenType = screenTypeMapper.getStandardById(cinemaAddNewDTO.getScreenTypeId());
//        if (screenType == null) {
//            String message = "添加影院失败！影厅类型【" + cinemaAddNewDTO.getScreenType() + "】不存在！";
//            log.warn(message);
//            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
//        }

        Cinema cinema = new Cinema();
        BeanUtils.copyProperties(cinemaAddNewDTO, cinema);
        cinemaMapper.insert(cinema);
    }

    @Override
    public void deleteById(Long id) {
        cinemaMapper.deleteById(id);
        log.info("删除电影院业务完成！");
    }

    @Override
    public void updateById(Long id, CinemaUpdateDTO cinemaUpdateDTO) {
        log.info("开始处理更新影院信息的业务！更新数据为：{}", cinemaUpdateDTO);
//        CinemaBrandStandardVO cinemaBrand = cinemaBrandMapper.getStandardById(cinemaUpdateDTO.getBrandId());
//        if (cinemaBrand == null) {
//            String message = "更新影院信息失败！商品id【" + cinemaUpdateDTO.getBrandId() + "】不存在！";
//            log.warn(message);
//            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
//        }
//
//        ScreenStandardVO screen = screenMapper.getStandardById(cinemaUpdateDTO.getScreenId());
//        if (screen == null) {
//            String message = "更新影院失败！影厅id【" + cinemaUpdateDTO.getScreenId() + "】不存在！";
//            log.warn(message);
//            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
//        }
//
//        ScreenStandardVO screenTypeId = screenMapper.getStandardById(cinemaUpdateDTO.getScreenTypeId());
//        if (screenTypeId == null) {
//            String message = "添加影厅类型失败！影厅类型id【" + cinemaUpdateDTO.getScreenTypeId() + "】不存在！";
//            log.warn(message);
//            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
//        }

        Cinema cinema = new Cinema();
        BeanUtils.copyProperties(cinemaUpdateDTO, cinema);
        cinema.setId(id);
        cinemaMapper.updateById(cinema);
        log.info("更新完成！");
    }

    @Override
    public List<CinemaListItemVO> list() {
        return cinemaMapper.list();
    }

    @Override
    public List<CinemaListItemVO> listByFilmId(Long filmId) {
        return cinemaMapper.getCinemaByFilmId(filmId);
    }
}
