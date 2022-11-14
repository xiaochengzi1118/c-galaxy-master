package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.ScreenType;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenTypeListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenTypeStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ScreenTypeTest {

    @Autowired
    ScreenTypeMapper mapper;

    @Test
    public void testInsert() {
        ScreenType screenType = new ScreenType();
        screenType.setName("imax厅");

        log.info("插入数据之前，参数={}", screenType);
        int rows = mapper.insert(screenType);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", screenType);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        ScreenType screenType = new ScreenType();
        screenType.setId(2L);
        screenType.setName("3d4k全大屏超清影厅");

        int rows = mapper.updateById(screenType);
        log.debug("修改相册信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中电影数量={}",rows);
    }

    @Test
    public void testCountByName(){
        String name = "imax影厅";
        int rows = mapper.countByName(name);
        log.info("当前表中名称为该名称的电影数量={}",rows);
    }

    @Test
    public void testGetStandardById(){
        Long id = 3L;
        ScreenTypeStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<ScreenTypeListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (ScreenTypeListItemVO film:list) {
            log.info("{}",film);
        }
    }


}
