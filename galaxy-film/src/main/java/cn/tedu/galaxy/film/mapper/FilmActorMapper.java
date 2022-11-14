package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.FilmActor;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmActorListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmActorStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmActorMapper {
    /**
     * 插入电影演员数据
     *
     * @param filmActor 电影演员数据
     * @return 受影响的行数
     */
    int insert(FilmActor filmActor);

    /**
     * 根据id删除电影演员
     *
     * @param id 被删除的电影演员的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改电影演员数据详情
     *
     * @param filmActor 封装了id与新数据的电影演员对象
     * @return 受影响的行数
     */
    int updateById(FilmActor filmActor);

    /**
     * 统计当前表中电影演员数据的数量
     *
     * @return 当前表中电影演员数据的数量
     */
    int count();


    /**
     * 根据id获取电影演员的标准信息
     *
     * @param id 相册id
     * @return 返回匹配的演员的标准信息，如果没有匹配的数据，将返回null
     */
    FilmActorStandardVO getStandardById(Long id);

    /**
     * 查询电影演员列表
     *
     * @return 演员列表
     */
    List<FilmActorListItemVO> list();


}
