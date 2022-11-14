package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.ScreenSeat;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenSeatListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenSeatStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenSeatMapper {
    /**
     * 插入影厅座位数据
     *
     * @param screenSeat 影厅座位数据
     * @return 受影响的行数
     */
    int insert(ScreenSeat screenSeat);

    /**
     * 根据id删除影厅座位
     *
     * @param id 被删除的影厅座位的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改影厅座位数据详情
     *
     * @param screenSeat 封装了id与新数据的影厅座位对象
     * @return 受影响的行数
     */
    int updateById(ScreenSeat screenSeat);

    /**
     * 统计当前表中影厅座位数据的数量
     *
     * @return 当前表中影厅座位数据的数量
     */
    int count();

    /**
     * 根据id获取影厅座位的标准信息
     *
     * @param id 影厅座位id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    ScreenSeatStandardVO getStandardById(Long id);

    /**
     * 查询影厅座位列表
     *
     * @return 影厅座位列表
     */
    List<ScreenSeatListItemVO> list();


}
