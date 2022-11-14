package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.FilmTypeAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmTypeUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmTypeListItemVO;

import java.util.List;

public interface IFilmTypeService {
    //新增电影类型
    void save(FilmTypeAddNewDTO filmTypeAddNewDTO);

    //根据id删除电影类型
    void deleteById(Long id);

    //根据id修改电影类型
    void updateById(Long id, FilmTypeUpdateDTO filmTypeUpdateDTO);

    //查询电影列表类型
    List<FilmTypeListItemVO> list();
}
