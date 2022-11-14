package cn.tedu.galaxy.order.controller;

import cn.tedu.galaxy.commons.pojo.film.dto.OrderAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.OrderUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Order;
import cn.tedu.galaxy.commons.pojo.film.vo.OrderListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.UserOrderStandardVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.order.service.IOrderService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "订单管理模块")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private IOrderService orderService;

    // 新增订单
    // http://localhost:9222/orders/save
    @ApiOperation("新增订单")
    @ApiOperationSupport(order = 100)
    @PostMapping("/save")
    public JsonResult<String> addOrder(@Validated OrderAddNewDTO orderAddNewDTO) {
        log.debug("开始处理【新增订单】的请求：{}", orderAddNewDTO);
        orderService.addOrder(orderAddNewDTO);
        return JsonResult.ok("新增订单成功!");
    }

    // 删除订单
    // http://localhost:9222/orders/{id}/delete
    @ApiOperation("删除订单")
    @ApiOperationSupport(order = 200)
    @GetMapping("/{id:[0-9]+}/delete")
    public JsonResult<String> deleteOrder(@PathVariable Long id) {
        log.debug("开始处理【删除订单】的请求：id={}", id);
        orderService.deleteById(id);
        return JsonResult.ok("删除成功!");
    }

    // 修改订单
    // http://localhost:9222/orders/{id}/update
    @ApiOperation("修改订单")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<String> updateOrder(@PathVariable @RequestBody Long id, @RequestBody OrderUpdateDTO orderUpdateDTO) {
        log.debug("开始处理【修改订单详情】的请求：id={} ,orderUpdateDTO={}", id, orderUpdateDTO);
        orderService.updateById(id, orderUpdateDTO);
        return JsonResult.ok("修改订单完成!");
    }

    // 订单列表
    // http://localhost:9222/orders
    @ApiOperation("订单列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult<List<OrderListItemVO>> orderList() {
        log.info("开始处理【查询订单列表】的业务");
        List<OrderListItemVO> list = orderService.list();
        return JsonResult.ok(list);
    }
    @ApiOperation("根据用户id查订单")
    @ApiOperationSupport(order = 500)
    @ApiImplicitParam(name = "UserId", value = "用户id", required = true, dataType = "long")
    @GetMapping("/{UserId:[0-9]+}/Userdetail")
    public JsonResult<JsonPage<UserOrderStandardVO>> getStandardByUserId(@PathVariable Long UserId, @PathVariable Integer page, @PathVariable Integer pageSize) {
        log.debug("开始处理【根据id查询订单】的请求：UserId={}", UserId);
        JsonPage<UserOrderStandardVO> standardByUserId = orderService.getStandardByUserId(UserId, page, pageSize);
        return JsonResult.ok(standardByUserId);
    }

    @GetMapping("/page/{page:[0-9]+}/{pageSize:[0-9]+}")
    @ApiOperation("分页查询所有订单")
    @ApiOperationSupport(order = 600)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name="page",example = "1"),
            @ApiImplicitParam(value = "每页条数",name="pageSize",example = "8")
    })
    public JsonResult<JsonPage<Order>> pageOrders(
            @PathVariable Integer page, @PathVariable Integer pageSize){
        JsonPage<Order> jsonPage=orderService.getAllOrdersByPage(page,pageSize);
        return JsonResult.ok(jsonPage);
    }

}
