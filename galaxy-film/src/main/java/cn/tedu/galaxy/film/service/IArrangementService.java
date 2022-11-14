package cn.tedu.galaxy.film.service;


import cn.tedu.galaxy.commons.pojo.film.dto.ArrangementAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ArrangementUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.RemainingReduceCountDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Arrangement;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListByFilmIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListItemVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IArrangementService {
    //减少剩余座位数
    @Transactional
    void reduceRemainingCount(RemainingReduceCountDTO remainingReduceCountDTO);

    //新增排片场次
    void save(ArrangementAddNewDTO arrangementAddNewDTO);

    //根据id删除排片场次
    void deleteById(Long id);

    //根据id修改排片场次
    void updateById(Long id, ArrangementUpdateDTO arrangementUpdateDTO);

    //查询排片场次
    List<ArrangementListItemVO> list();

    Arrangement getArrangementById(Long id);

    List<ArrangementListByFilmIdVO> listByFilmId(Long filmId);

    //LocalDateTime DateListByCinemaIdAndFilmId(Long cinemaId,Long filmId);

    JsonPage<Arrangement> getAllArrangementByPage(Integer page, Integer pageSize);
}
