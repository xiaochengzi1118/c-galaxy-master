package cn.tedu.galaxy.film.mapper;


import cn.tedu.galaxy.commons.pojo.film.entity.Arrangement;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListByFilmIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementStandardVO;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ArrangementMapper {
    /**
     * 插入场次数据
     *
     * @param arrangement 场次数据
     * @return 受影响的行数
     */
    int insert(Arrangement arrangement);

    /**
     * 根据id删除场次
     *
     * @param id 被删除的场次的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改场次数据详情
     *
     * @param arrangement 封装了id与新数据的场次对象
     * @return 受影响的行数
     */
    int updateById(Arrangement arrangement);

    /**
     * 统计当前表中场次数据的数量
     *
     * @return 当前表中场次数据的数量
     */
    int count();

    /**
     * 根据id获取场次的标准信息
     *
     * @param id 场次id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    ArrangementStandardVO getStandardById(Long id);

    Arrangement getArrangementById(Long id);

    /**
     * 查询场次列表
     *
     * @return 场次列表
     */
    List<ArrangementListItemVO> list();

    List<ArrangementListItemVO> listByScreenName(String screenName);

    /**
     * 根据场次id获取剩余座位数的标准信息
     *
     * @param id 场次id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    int getRemainingById(Long id);

    /**
     * 根据场次id获取减少座位数的标准信息
     *
     * @param id 场次id
     */
    //场次减少座位数
    int updateRemaining(Long id,Integer reduceCount);

    //根据电影id和日期查询场次
    List<ArrangementListByFilmIdVO> listByFilmId(Long filmId);

    //分页查询搜索所有场次
    List<Arrangement> findAllArrangements();
}
