package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.CinemaAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.CinemaUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaListItemVO;

import java.util.List;

public interface ICinemaService {
    //新增影院
    void save(CinemaAddNewDTO cinemaAddNewDTO);

    //根据id删除影院
    void deleteById(Long id);

    //根据id修改影院
    void updateById(Long id, CinemaUpdateDTO cinemaUpdateDTO);

    //查询影院列表
    List<CinemaListItemVO> list();

    //根据电影id查询影院
    List<CinemaListItemVO> listByFilmId(Long filmId);
}
