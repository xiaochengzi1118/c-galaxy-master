package cn.tedu.galaxy.order.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Order;
import cn.tedu.galaxy.commons.pojo.film.vo.OrderListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.OrderStandardVO;
import cn.tedu.galaxy.commons.pojo.film.vo.UserOrderStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {
    /**
     * 插入订单数据
     *
     * @param order 订单数据
     * @return 受影响的行数
     */
    int insert(Order order);

    /**
     * 根据id删除订单
     *
     * @param id 被删除的订单的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改订单数据详情
     *
     * @param order 封装了id与新数据的订单对象
     * @return 受影响的行数
     */
    int updateById(Order order);

    /**
     * 统计当前表中订单数据的数量
     *
     * @return 当前表中订单数据的数量
     */
    int count();

    /**
     * 根据id获取订单的标准信息
     *
     * @param id 订单id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    Order getStandardById(Long id);

    /**
     * 根据id获取订单的标准信息
     *
     * @param userId 订单id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    List<UserOrderStandardVO> getStandardByUserId(Long userId);

    /**
     * 查询订单列表
     *
     * @return 订单列表
     */
    List<OrderListItemVO> list();

    List<Order> findAllOrders();
}
