package cn.tedu.galaxy.order;

import cn.tedu.galaxy.commons.pojo.film.entity.Order;
import cn.tedu.galaxy.order.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GalaxyOrderApplicationTests {
    @Autowired
    OrderMapper orderMapper;

    @Test
    void contextLoads() {
        Order result = orderMapper.getStandardById(Long.valueOf("2121022"));
        if (result != null) {
            System.out.println("真实的");
        }
    }



}
