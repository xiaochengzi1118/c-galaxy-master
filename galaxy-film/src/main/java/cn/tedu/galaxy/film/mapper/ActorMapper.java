package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Actor;
import cn.tedu.galaxy.commons.pojo.film.vo.ActorListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ActorStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorMapper {
    /**
     * 插入演员数据
     *
     * @param actor 演员数据
     * @return 受影响的行数
     */
    int insert(Actor actor);

    /**
     * 根据id删除演员
     *
     * @param id 被删除的演员的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改演员数据详情
     *
     * @param actor 封装了id与新数据的演员对象
     * @return 受影响的行数
     */
    int updateById(Actor actor);

    /**
     * 统计当前表中演员数据的数量
     *
     * @return 当前表中演员数据的数量
     */
    int count();

    /**
     * 根据演员名称统计当前表中演员数据的数量
     *
     * @param name 演员名称
     * @return 当前表中匹配名称的演员的数量
     */
    int countByName(String name);

    /**
     * 根据id获取演员的标准信息
     *
     * @param id 演员id
     * @return 返回匹配的演员的标准信息，如果没有匹配的数据，将返回null
     */
    ActorStandardVO getStandardById(Long id);

    /**
     * 查询演员列表
     *
     * @return 演员列表
     */
    List<ActorListItemVO> list();
}
