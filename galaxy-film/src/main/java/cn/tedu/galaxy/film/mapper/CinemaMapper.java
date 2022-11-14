package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Cinema;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaMapper {
    /**
     * 插入电影院数据
     *
     * @param cinema 电影院数据
     * @return 受影响的行数
     */
    int insert(Cinema cinema);

    /**
     * 根据id删除电影院
     *
     * @param id 被删除的电影院的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改电影院数据详情
     *
     * @param cinema 封装了id与新数据的电影院对象
     * @return 受影响的行数
     */
    int updateById(Cinema cinema);

    /**
     * 统计当前表中电影院数据的数量
     *
     * @return 当前表中电影院数据的数量
     */
    int count();

    /**
     * 根据电影院名称统计当前表中电影院数据的数量
     *
     * @param name 电影院名称
     * @return 当前表中匹配名称的电影院的数量
     */
    int countByName(String name);

    /**
     * 根据id获取电影院的标准信息
     *
     * @param id 相册id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    CinemaStandardVO getStandardById(Long id);

    /**
     * 查询电影院列表
     *
     * @return 电影院列表
     */
    List<CinemaListItemVO> list();

    /**
     * 根据电影id获取影院的标准信息
     *
     * @param filmId 电影id
     */
    //根据电影id查询影院
    List<CinemaListItemVO> getCinemaByFilmId(Long filmId);
}
