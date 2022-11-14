package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.CinemaBrand;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaBrandListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaBrandStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CinemaBrandTest {

    @Autowired
    CinemaBrandMapper mapper;

    @Test
    public void testInsert() {
        CinemaBrand cinemaBrand = new CinemaBrand();
        cinemaBrand.setName("华谊兄弟影院");

        log.info("插入数据之前，参数={}", cinemaBrand);
        int rows = mapper.insert(cinemaBrand);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", cinemaBrand);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        CinemaBrand cinemaBrand = new CinemaBrand();
        cinemaBrand.setId(4L);
        cinemaBrand.setName("仓山万达国际影院");

        int rows = mapper.updateById(cinemaBrand);
        log.debug("修改相册信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中电影数量={}",rows);
    }

    @Test
    public void testCountByName(){
        String name = "万达影院";
        int rows = mapper.countByName(name);
        log.info("当前表中名称为该名称的电影数量={}",rows);
    }

    @Test
    public void testGetStandardById(){
        Long id = 1L;
        CinemaBrandStandardVO cinemaBrandStandardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,cinemaBrandStandardVO);
    }

    @Test
    public void testList(){
        List<CinemaBrandListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (CinemaBrandListItemVO film:list) {
            log.info("{}",film);
        }
    }
}
