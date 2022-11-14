package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Actor;
import cn.tedu.galaxy.commons.pojo.film.entity.Cinema;
import cn.tedu.galaxy.commons.pojo.film.vo.ActorListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ActorStandardVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class ActorTest {

    @Autowired
    ActorMapper mapper;

    @Test
    public void testInsert() {
        Actor actor = new Actor();
        actor.setName("周润发");

        log.info("插入数据之前，参数={}", actor);
        int rows = mapper.insert(actor);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", actor);
    }

    @Test
    public void testDeleteById(){
        Long id = 2L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Actor actor = new Actor();
        actor.setId(1L);
        actor.setName("周杰伦");

        int rows = mapper.updateById(actor);
        log.debug("修改演员信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中演员数量={}",rows);
    }

    @Test
    public void testCountByName(){
        String name = "周润发";
        int rows = mapper.countByName(name);
        log.info("当前表中名称为该名称的演员数量={}",rows);
    }

    @Test
    public void testGetStandardById(){
        Long id = 1L;
        ActorStandardVO actorStandardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的演员详情为:{}",id,actorStandardVO);
    }

    @Test
    public void testList(){
        List<ActorListItemVO> list = mapper.list();
        log.debug("查询演员列表，查询结果中的数据的数量：{}", list.size());
        for (ActorListItemVO film:list) {
            log.info("{}",film);
        }
    }
}
