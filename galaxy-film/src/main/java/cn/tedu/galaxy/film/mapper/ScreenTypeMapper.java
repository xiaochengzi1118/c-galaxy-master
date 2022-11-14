package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.ScreenType;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenTypeListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenTypeStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenTypeMapper {
    /**
     * 插入影厅类型数据
     *
     * @param screenType 影厅类型数据
     * @return 受影响的行数
     */
    int insert(ScreenType screenType);

    /**
     * 根据id删除影厅类型
     *
     * @param id 被删除的影厅类型的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改影厅类型数据详情
     *
     * @param screenType 封装了id与新数据的影厅类型对象
     * @return 受影响的行数
     */
    int updateById(ScreenType screenType);

    /**
     * 统计当前表中影厅类型数据的数量
     *
     * @return 当前表中影厅类型数据的数量
     */
    int count();

    /**
     * 根据影厅类型名称统计当前表中电厅数据的数量
     *
     * @param name 影厅类型名称
     * @return 当前表中匹配名称的电厅数据的数量
     */
    int countByName(String name);

    /**
     * 根据id获取影厅类型的标准信息
     *
     * @param id 影厅类型id
     * @return 返回匹配的影厅类型的标准信息，如果没有匹配的数据，将返回null
     */
    ScreenTypeStandardVO getStandardById(Long id);

    /**
     * 查询影厅类型列表
     *
     * @return 影厅类型列表
     */
    List<ScreenTypeListItemVO> list();
}
