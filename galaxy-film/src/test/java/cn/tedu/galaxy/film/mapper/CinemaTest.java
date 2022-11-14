package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Cinema;
import cn.tedu.galaxy.commons.pojo.film.entity.Film;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaStandardVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CinemaTest {

    @Autowired
    CinemaMapper mapper;

    @Test
    public void testInsert() {
        Cinema cinema = new Cinema();
        cinema.setName("仓山万达imax影院");

        log.info("插入数据之前，参数={}", cinema);
        int rows = mapper.insert(cinema);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", cinema);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Cinema cinema = new Cinema();
        cinema.setId(4L);
        cinema.setName("仓山万达国际影院");

        int rows = mapper.updateById(cinema);
        log.debug("修改相册信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中电影数量={}",rows);
    }

    @Test
    public void testCountByName(){
        String name = "大学城店";
        int rows = mapper.countByName(name);
        log.info("当前表中名称为该名称的电影数量={}",rows);
    }

    @Test
    public void testGetStandardById(){
        Long id = 1L;
        CinemaStandardVO cinemaStandardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,cinemaStandardVO);
    }

    @Test
    public void testList(){
        List<CinemaListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (CinemaListItemVO film:list) {
            log.info("{}",film);
        }
    }
}
