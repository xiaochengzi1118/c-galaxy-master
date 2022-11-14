package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Score;
import cn.tedu.galaxy.commons.pojo.film.vo.ScoreListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScoreStandardVO;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ScoreMapper {
    /**
     * 插入评分数据
     *
     * @param score 评分数据
     * @return 受影响的行数
     */
    int insert(Score score);

    /**
     * 根据id删除评分
     *
     * @param id 被删除的评分的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改评分数据详情
     *
     * @param score 封装了id与新评分的评分对象
     * @return 受影响的行数
     */
    int updateById(Score score);

    /**
     * 统计当前表中评分数据的数量
     *
     * @return 当前表中评分数据的数量
     */
    int count();

    /**
     * 根据id获取评分的标准信息
     *
     * @param id 评分id
     * @return 返回匹配的评分的标准信息，如果没有匹配的数据，将返回null
     */
    ScoreStandardVO getStandardById(Long id);
    /**
     * 根据id获取评分的标准信息
     *
     * @param fid 电影id
     * @param uid 用户id
     * @return 返回匹配的评分的标准信息，如果没有匹配的数据，将返回null
     */
    ScoreStandardVO getStandardByFidAndUid(Long fid,Long uid);
    /**
     * 查询评分列表
     *
     * @return 评分列表
     */
    List<ScoreListItemVO> list();

    /**
     * 根据电影id获取评分的标准信息
     *
     * @param id 电影id
     * @return 返回匹配的评分的标准信息，如果没有匹配的数据，将返回null
     */
    BigDecimal getScoreByFilmId(Long id);


}
