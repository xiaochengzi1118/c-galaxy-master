package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.ArrangementAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ArrangementUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.RemainingReduceCountDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Arrangement;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListByFilmIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListItemVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import cn.tedu.galaxy.film.mapper.ArrangementMapper;
import cn.tedu.galaxy.film.mapper.CinemaMapper;
import cn.tedu.galaxy.film.mapper.FilmMapper;
import cn.tedu.galaxy.film.mapper.ScreenMapper;
import cn.tedu.galaxy.film.service.IArrangementService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@DubboService
@Slf4j
@Service
public class ArrangementServiceImpl implements IArrangementService {
    @Autowired
    private ArrangementMapper arrangementMapper;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private CinemaMapper cinemaMapper;
    @Autowired
    private ScreenMapper screenMapper;

    @Override
    public void reduceRemainingCount(RemainingReduceCountDTO remainingReduceCountDTO) {
        // 调用持久层减少库存的方法
        // 参数1:场次id
        // 参数2:要减少的座位数
        arrangementMapper.updateRemaining(remainingReduceCountDTO.getId(),remainingReduceCountDTO.getReduceCount());
        log.info("座位减少已经完成!");
    }

    @Override
    public void save(ArrangementAddNewDTO arrangementAddNewDTO) {
        int countFilm = filmMapper.countByName(arrangementAddNewDTO.getFilmName());
        if (countFilm == 0) {
            // 是：演员名称已经存在，抛出RuntimeException异常
            String message = "添加排片失败！电影【" + arrangementAddNewDTO.getFilmName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        int countCinema = cinemaMapper.countByName(arrangementAddNewDTO.getCinemaName());
        if (countCinema == 0) {
            // 是：演员名称已经存在，抛出RuntimeException异常
            String message = "添加排片失败！影院【" + arrangementAddNewDTO.getCinemaName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        int countScreen = screenMapper.countByName(arrangementAddNewDTO.getScreenName());
        if (countScreen == 0) {
            String message = "添加排片失败！影厅【" + arrangementAddNewDTO.getScreenName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
//        List<ArrangementListItemVO> listByScreenName = arrangementMapper.listByScreenName(arrangementAddNewDTO.getScreenName());
//        if (listByScreenName != null) {
//            for (ArrangementListItemVO arrangementListItemVO : listByScreenName) {
//                if ((arrangementAddNewDTO.getGmtStart().isAfter(arrangementListItemVO.getGmtStart()) && arrangementAddNewDTO.getGmtStart().isBefore(arrangementListItemVO.getGmtEnd()))
//                        || (arrangementAddNewDTO.getGmtEnd().isAfter(arrangementListItemVO.getGmtStart()) && arrangementAddNewDTO.getGmtEnd().isBefore(arrangementListItemVO.getGmtEnd()))) {
//                    String message = "添加排片失败！影厅【" + arrangementAddNewDTO.getScreenName() + "】排片冲突！";
//                    log.warn(message);
//                    throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
//                }
//            }
//        }
        Arrangement arrangement = new Arrangement();
        BeanUtils.copyProperties(arrangementAddNewDTO, arrangement);
        arrangementMapper.insert(arrangement);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理根据id：{}删除场次的业务", id);
        arrangementMapper.deleteById(id);
        log.info("删除完成！");
    }

    @Override
    public void updateById(Long id, ArrangementUpdateDTO arrangementUpdateDTO) {
        log.info("开始处理更新场次的业务！id:{},更新数据为：{}", id, arrangementUpdateDTO);
        int countCinema = cinemaMapper.countByName(arrangementUpdateDTO.getCinemaName());
        if (countCinema == 0) {
            String message = "更新排片失败！影院【" + arrangementUpdateDTO.getCinemaName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        int countScreen = screenMapper.countByName(arrangementUpdateDTO.getScreenName());
        if (countScreen == 0) {
            String message = "更新排片失败！影厅【" + arrangementUpdateDTO.getScreenName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        int countFilm = filmMapper.countByName(arrangementUpdateDTO.getFilmName());
        if (countFilm == 0) {
            String message = "添加排片失败！电影【" + arrangementUpdateDTO.getFilmName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        Arrangement arrangement = new Arrangement();
        BeanUtils.copyProperties(arrangementUpdateDTO, arrangement);
        arrangement.setId(id);
        arrangementMapper.updateById(arrangement);
        log.info("更新完成！");
    }

    @Override
    public List<ArrangementListItemVO> list() {
        return arrangementMapper.list();
    }

    @Override
    public Arrangement getArrangementById(Long id) {
        return arrangementMapper.getArrangementById(id);
    }

    @Override
    public List<ArrangementListByFilmIdVO> listByFilmId(Long filmId) {
        return arrangementMapper.listByFilmId(filmId);
    }


    // 分页查询所有订单信息的方法
    // page是页码,pageSize是每页条数
    public JsonPage<Arrangement> getAllArrangementByPage(Integer page, Integer pageSize){
        // PageHelper框架实现分页功能最核心的代码要编写在执行查询的代码之前
        // 使用指定的代码设置要查询的页码和每页的条数
        // 在后面执行查询时,会自动按照这里指定的数据执行分页查询
        // page是页码,1就是查询第一页,pageSize是每页条数
        PageHelper.startPage(page,pageSize);
        // 下面执行查询操作,这个操作会被PageHelper框架在运行的sql语句末尾添加limit语句
        List<Arrangement> list= arrangementMapper.findAllArrangements();

        // list中的数据就是按照分页条件查询出来的某一页的数据
        // 但是分页查询方法返回的并不是list类型的对象,我们也需要获得分页的信息
        // 然后将查询出的数据和分页信息结合返回,这个类型,就是PageInfo

        return JsonPage.restPage(new PageInfo<>(list));

    }

}
