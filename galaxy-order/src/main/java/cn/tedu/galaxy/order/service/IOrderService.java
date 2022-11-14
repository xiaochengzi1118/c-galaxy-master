package cn.tedu.galaxy.order.service;


import cn.tedu.galaxy.commons.pojo.film.dto.OrderAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.OrderUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Order;
import cn.tedu.galaxy.commons.pojo.film.vo.OrderListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.UserOrderStandardVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IOrderService {
    //新增订单
    @Transactional
    void addOrder(OrderAddNewDTO orderAddNewDTO);

    //根据id删除订单
    void deleteById(Long id);

    //根据id修改订单
    void updateById(Long id, OrderUpdateDTO orderUpdateDTO);

    //查询订单列表
    List<OrderListItemVO> list();

    JsonPage<UserOrderStandardVO> getStandardByUserId(Long userId,Integer page, Integer pageSize);

    JsonPage<Order> getAllOrdersByPage(Integer page, Integer pageSize);
}
