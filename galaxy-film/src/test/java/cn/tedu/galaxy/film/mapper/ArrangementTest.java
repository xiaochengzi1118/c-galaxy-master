package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Arrangement;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListByFilmIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ArrangementTest {

    @Autowired
    ArrangementMapper mapper;

    @Test
    public void testInsert() {
        Arrangement arrangement = new Arrangement();
        arrangement.setFilmName("谢顶后的山峰");

        log.info("插入数据之前，参数={}", arrangement);
        int rows = mapper.insert(arrangement);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", arrangement);
    }

    @Test
    public void testDeleteById(){
        Long id = 4L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Arrangement arrangement = new Arrangement();
        arrangement.setId(5L);
        arrangement.setFilmName("我的小弟之顺峰");

        int rows = mapper.updateById(arrangement);
        log.debug("修改场次信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中场次数量={}",rows);
    }


    @Test
    public void testGetStandardById(){
        Long id = 2L;
        ArrangementStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的场次详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<ArrangementListItemVO> list = mapper.list();
        log.debug("查询场次列表，查询结果中的数据的数量：{}", list.size());
        for (ArrangementListItemVO film:list) {
            log.info("{}",film);
        }
    }

    @Test
    void testListByFilmId(){
        List<ArrangementListByFilmIdVO> list = mapper.listByFilmId(1L);
        for (ArrangementListByFilmIdVO film:list) {
            log.info("{}",film);
        }
    }

}
