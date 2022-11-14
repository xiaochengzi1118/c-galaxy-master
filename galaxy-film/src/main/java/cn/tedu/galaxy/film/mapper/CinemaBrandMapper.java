package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.CinemaBrand;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaBrandListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaBrandStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaBrandMapper {
    /**
     * 插入影院品牌数据
     *
     * @param cinemaBrand 影院品牌数据
     * @return 受影响的行数
     */
    int insert(CinemaBrand cinemaBrand);

    /**
     * 根据id删除影院品牌
     *
     * @param id 被删除的影院品牌的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改影院品牌数据详情
     *
     * @param cinemaBrand 封装了id与新数据的影院品牌对象
     * @return 受影响的行数
     */
    int updateById(CinemaBrand cinemaBrand);

    /**
     * 统计当前表中影院品牌数据的数量
     *
     * @return 当前表中影院品牌数据的数量
     */
    int count();

    /**
     * 根据影院品牌名称统计当前表中影院品牌数据的数量
     *
     * @param name 影院品牌名称
     * @return 当前表中匹配名称的影院品牌的数量
     */
    int countByName(String name);

    /**
     * 根据id获取影院品牌的标准信息
     *
     * @param id 影院品牌id
     * @return 返回匹配的影院品牌的标准信息，如果没有匹配的数据，将返回null
     */
    CinemaBrandStandardVO getStandardById(Long id);

    /**
     * 查询影院品牌列表
     *
     * @return 影院品牌列表
     */
    List<CinemaBrandListItemVO> list();
}
