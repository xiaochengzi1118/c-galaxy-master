package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.FilmType;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmStandardVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmTypeListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmTypeStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FilmTypeTest {

    @Autowired
    FilmTypeMapper mapper;

    @Test
    public void testInsert() {
        FilmType filmType = new FilmType();
        filmType.setName("谢顶后的山峰");

        log.info("插入数据之前，参数={}", filmType);
        int rows = mapper.insert(filmType);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", filmType);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        FilmType filmType = new FilmType();
        filmType.setId(2L);
        filmType.setName("我的小弟之顺峰");

        int rows = mapper.updateById(filmType);
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
        FilmTypeStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
//        List<FilmTypeListItemVO> list = mapper.list();
//        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
//        for (FilmTypeListItemVO film:list) {
//            log.info("{}",film);
//        }
    }


}
