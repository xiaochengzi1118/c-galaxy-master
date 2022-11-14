package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Screen;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenMapper {
    /**
     * 插入影厅数据
     *
     * @param screen 影厅数据
     * @return 受影响的行数
     */
    int insert(Screen screen);

    /**
     * 根据id删除影厅
     *
     * @param id 被删除的影厅的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改影厅数据详情
     *
     * @param screen 封装了id与新数据的影厅对象
     * @return 受影响的行数
     */
    int updateById(Screen screen);

    /**
     * 统计当前表中影厅数据的数量
     *
     * @return 当前表中影厅数据的数量
     */
    int count();

    /**
     * 根据影厅名称统计当前表中电厅数据的数量
     *
     * @param name 影厅名称
     * @return 当前表中匹配名称的影厅数据的数量
     */
    int countByName(String name);

    /**
     * 根据id获取影厅的标准信息
     *
     * @param id 影厅id
     * @return 返回匹配的影厅的标准信息，如果没有匹配的数据，将返回null
     */
    ScreenStandardVO getStandardById(Long id);

    /**
     * 查询影厅列表
     *
     * @return 影厅列表
     */
    List<ScreenListItemVO> list();

    Integer getRoByName(String name);

    Integer getCoByName(String name);


}
