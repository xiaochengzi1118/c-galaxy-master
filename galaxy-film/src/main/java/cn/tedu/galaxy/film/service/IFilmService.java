package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.FilmAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Film;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListByCinemaIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IFilmService {
    //新增电影
    void save(FilmAddNewDTO filmAddNewDTO);

    //根据id删除电影
    void deleteById(Long id);

    //根据id修改电影
    void updateById(FilmUpdateDTO filmUpdateDTO);


    //查询电影列表
    List<FilmListItemVO> list();

    List<FilmListItemVO> listBy(String type,String region,String year);

    //    List<Film> findByRegionAndType(String region, String type);
//
    //获取热门电影
    List<FilmListItemVO> findHots();

    //
//
    Film findFilmById(Long id);

    //    Film update(Film film);
    //根据电影名模糊查询
//    List<FilmListItemVO> findFilmLikeName(String name);

    List<FilmListItemVO> findAllFilm(int state);

//    List<FilmListItemVO> findFilmLikeType(String type);

//    List<FilmListItemVO> findByRegionAndType(String region, String type);

    List<FilmListByCinemaIdVO> listByCinemaId(Long cinemaId);
}

