package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.FilmActorAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmActorUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmActorListItemVO;

import java.util.List;

public interface IFilmActorService {
    //新增电影
    void save(FilmActorAddNewDTO filmActorAddNewDTO);

    //根据id删除电影
    void deleteById(Long id);

    //根据id修改电影
    void updateById(Long id, FilmActorUpdateDTO filmActorUpdateDTO);

    //查询电影列表
    List<FilmActorListItemVO> list();
}
