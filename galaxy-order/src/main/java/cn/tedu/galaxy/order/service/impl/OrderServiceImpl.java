package cn.tedu.galaxy.order.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.OrderAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.OrderUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.RemainingReduceCountDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Order;
import cn.tedu.galaxy.commons.pojo.film.vo.OrderListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.UserOrderStandardVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import cn.tedu.galaxy.film.mapper.ArrangementMapper;
import cn.tedu.galaxy.film.mapper.CinemaMapper;
import cn.tedu.galaxy.film.mapper.FilmMapper;
import cn.tedu.galaxy.film.mapper.ScreenMapper;
import cn.tedu.galaxy.film.service.IArrangementService;
import cn.tedu.galaxy.order.mapper.OrderMapper;
import cn.tedu.galaxy.order.service.IOrderService;
import cn.tedu.galaxy.order.util.IdUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@DubboService
@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private OrderMapper orderMapper;

    // 添加@DubboReference注解,表示当前业务逻辑层要消费其他模块的服务
    // 可以编写当前Nacos中注册的其他业务模块的逻辑层接口
    // 因为Nacos中注册了业务的实现类,所以声明的接口会自动匹配到实现类对象
    // 先添加stock模块的业务逻辑层接口,在添加cart模块
    @DubboReference
    private FilmMapper filmMapper;
    @DubboReference
    private CinemaMapper cinemaMapper;
    @DubboReference
    private ScreenMapper screenMapper;
    @DubboReference
    private ArrangementMapper arrangementMapper;
    @DubboReference
    private IArrangementService arrangementService;

    @Override
    public void addOrder(OrderAddNewDTO orderAddNewDTO) {
        // 1.减少订单中场次的剩余座位数(要调用film模块的arrangement减少剩余座位数功能)
        RemainingReduceCountDTO remainingReduceCountDTO = new RemainingReduceCountDTO();
        remainingReduceCountDTO.setId(orderAddNewDTO.getArrangementId());
        remainingReduceCountDTO.setReduceCount(1);
        arrangementService.reduceRemainingCount(remainingReduceCountDTO);
        // 2.将orderAddDTO中的信息新增到数据库订单表中
        // 要将orderAddDTO对象中的属性赋值给Order类型对象的同名属性

        Order order=new Order();
        BeanUtils.copyProperties(orderAddNewDTO,order);
        // 获取随机订单号
        Long id = IdUtils.getId();
        order.setId(id);
        order.setState(0);
        // 执行新增
        orderMapper.insert(order);
        log.info("新增订单信息为:{}",order);
    }

    @Override
    public void deleteById(Long id) {
        orderMapper.deleteById(id);
        log.info("订单号={},删除成功!", id);
    }

    @Override
    public void updateById(Long id, OrderUpdateDTO orderUpdateDTO) {
        //判断订单中电影是否存在
        int countFilm = filmMapper.countByName(orderUpdateDTO.getFilmName());
        if (countFilm == 0) {
            String message = "添加订单失败！电影【" + orderUpdateDTO.getFilmName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //判断订单中影院是否存在
        int countCinema = cinemaMapper.countByName(orderUpdateDTO.getCinemaName());
        if (countCinema == 0) {
            String message = "添加订单失败！影院【" + orderUpdateDTO.getCinemaName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //判断订单中影厅是否存在
        int countScreen = screenMapper.countByName(orderUpdateDTO.getScreenName());
        if (countScreen == 0) {
            String message = "添加订单失败！影厅【" + orderUpdateDTO.getScreenName() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        //判断场次剩余座位是否大于0
        int remaining = arrangementMapper.getRemainingById(orderUpdateDTO.getArrangementId());
        if (remaining <= 0) {
            String message = "添加订单失败！本场票已售空！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        Order order = new Order();
        BeanUtils.copyProperties(orderUpdateDTO, order);
        orderMapper.updateById(order);
        log.info("订单修改成功!订单信息为:{}", order);
    }

    @Override
    public List<OrderListItemVO> list() {
        return orderMapper.list();
    }

    @Override
    public JsonPage<UserOrderStandardVO> getStandardByUserId(Long userId,Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        log.debug("页数{},页数条数{}",page,pageSize);
        List<UserOrderStandardVO> standardByUserId = orderMapper.getStandardByUserId(userId);
        if (standardByUserId==null){
            String message = "查看订单失败没有订单！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        return JsonPage.restPage(new PageInfo<>(standardByUserId));
    }

    @Override
    public JsonPage<Order> getAllOrdersByPage(Integer page, Integer pageSize) {

        // PageHelper框架实现分页功能最核心的代码要编写在执行查询的代码之前
        // 使用指定的代码设置要查询的页码和每页的条数
        // 在后面执行查询时,会自动按照这里指定的数据执行分页查询
        // page是页码,1就是查询第一页,pageSize是每页条数
        PageHelper.startPage(page,pageSize);
        // 下面执行查询操作,这个操作会被PageHelper框架在运行的sql语句末尾添加limit语句
        List<Order> list= orderMapper.findAllOrders();

        // list中的数据就是按照分页条件查询出来的某一页的数据
        // 但是分页查询方法返回的并不是list类型的对象,我们也需要获得分页的信息
        // 然后将查询出的数据和分页信息结合返回,这个类型,就是PageInfo

        return JsonPage.restPage(new PageInfo<>(list));

    }
}
