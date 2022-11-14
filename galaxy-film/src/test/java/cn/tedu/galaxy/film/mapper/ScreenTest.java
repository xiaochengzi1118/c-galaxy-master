package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Screen;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ScreenTest {

    @Autowired
    ScreenMapper mapper;

    @Test
    public void testInsert() {
        Screen screen = new Screen();
        screen.setName("测试1");

        log.info("插入数据之前，参数={}", screen);
        int rows = mapper.insert(screen);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", screen);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Screen screen = new Screen();
        screen.setId(2L);
        screen.setName("测试1");

        int rows = mapper.updateById(screen);
        log.debug("修改相册信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中电影数量={}",rows);
    }

    @Test
    public void testGetStandardById(){
        Long id = 3L;
        ScreenStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<ScreenListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (ScreenListItemVO film:list) {
            log.info("{}",film);
        }
    }


}
