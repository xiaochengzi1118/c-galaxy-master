package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.ScreenSeat;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenSeatListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenSeatStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ScreenSeatTest {

    @Autowired
    ScreenSeatMapper mapper;

    @Test
    public void testInsert() {
        ScreenSeat screenSeat = new ScreenSeat();
        screenSeat.setScreenId(1);

        log.info("插入数据之前，参数={}", screenSeat);
        int rows = mapper.insert(screenSeat);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", screenSeat);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        ScreenSeat screenSeat = new ScreenSeat();
        screenSeat.setId(2L);
        screenSeat.setScreenId(1);

        int rows = mapper.updateById(screenSeat);
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
        ScreenSeatStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<ScreenSeatListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (ScreenSeatListItemVO film:list) {
            log.info("{}",film);
        }
    }


}
