package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Film;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FilmTest {

    @Autowired
    FilmMapper mapper;

    @Test
    public void testInsert() {
        Film film = new Film();
        film.setName("谢顶后的山峰");

        log.info("插入数据之前，参数={}", film);
        int rows = mapper.insert(film);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", film);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Film film = new Film();
        film.setId(2L);
        film.setName("我的小弟之顺峰");

        int rows = mapper.updateById(film);
        log.debug("修改相册信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中电影数量={}",rows);
    }

    @Test
    public void testCountByName(){
        int rows = mapper.countByName("我的小弟之顺峰");
        log.info("当前表中名称为该名称的电影数量={}",rows);
    }

    @Test
    public void testGetStandardById(){
        Long id = 3L;
        FilmStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<FilmListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (FilmListItemVO film:list) {
            log.info("{}",film);
        }
    }

    @Test
    public void testListBy(){
        List<FilmListItemVO> list = mapper.listByTypeRegionYear("冒险","国","2019");
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (FilmListItemVO film:list) {
            log.info("{}",film);
        }
    }

    @Test
    public void testState(){
        List<FilmListItemVO> allFilm = mapper.findAllFilm(1);
        allFilm.forEach(film -> System.out.println(film));
    }

}
