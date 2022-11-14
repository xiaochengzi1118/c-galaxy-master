package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Film;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListByCinemaIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmMapper {
    /**
     * 插入电影数据
     *
     * @param film 电影数据
     * @return 受影响的行数
     */
    int insert(Film film);

    /**
     * 根据id删除电影
     *
     * @param id 被删除的电影的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改电影数据
     *
     * @param film 封装了id与新数据的电影对象
     * @return 受影响的行数
     */
    int updateById(Film film);

    /**
     * 统计当前表中电影数据的数量
     *
     * @return 当前表中电影数据的数量
     */
    int count();

    /**
     * 根据电影名称统计当前表中电影数据的数量
     *
     * @param name 电影名称
     * @return 当前表中匹配名称的电影数据的数量
     */
    int countByName(String name);

    /**
     * 根据id获取电影的标准信息
     *
     * @param id 相册id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    FilmStandardVO getStandardById(Long id);

    /**
     * 查询电影列表
     *
     * @return 电影列表
     */
    List<FilmListItemVO> list();

    /**
     * 根据条件查询电影列表
     *
     * @return 电影列表
     */
    List<FilmListItemVO> listByTypeRegionYear(String type,String region,String year);

    /**
     * 根据电影id查询电影对象
     *
     * @param id
     * @return
     */
    Film findFilmById(Long id);

    /**
     * 根据电影名查询电影对象
     *
     * @param name
     * @return
     */
    Film findFilmByName(String name);

    List<FilmListItemVO> findAllFilm(int state);

    List<FilmListItemVO> findFilmLikeName(String name);

    List<FilmListItemVO> findFilmLikeType(String type);
//    List<FilmListItemVO> findByRegionAndType(String region, String type);
    /**
     * 获取热门电影
     *
     * @return
     */
    List<FilmListItemVO> findHots();

    /**
     * 根据影院id查询电影
     *
     * @return List<FilmListByCinemaIdVO>
     */
    List<FilmListByCinemaIdVO> listByCinemaId(Long cinemaId);
}
