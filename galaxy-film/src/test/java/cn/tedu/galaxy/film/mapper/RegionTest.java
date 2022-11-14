package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Region;
import cn.tedu.galaxy.commons.pojo.film.vo.RegionListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.RegionStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class RegionTest {

    @Autowired
    RegionMapper mapper;

    @Test
    public void testInsert() {
        Region region = new Region();
        region.setName("福州");

        log.info("插入数据之前，参数={}", region);
        int rows = mapper.insert(region);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", region);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Region region = new Region();
        region.setId(2L);
        region.setName("上海");

        int rows = mapper.updateById(region);
        log.debug("修改相册信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中电影数量={}",rows);
    }

    @Test
    public void testCountByName(){
        int rows = mapper.countByName("福州");
        log.info("当前表中名称为该名称的电影数量={}",rows);
    }

    @Test
    public void testGetStandardById(){
        Long id = 3L;
        RegionStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<RegionListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (RegionListItemVO film:list) {
            log.info("{}",film);
        }
    }


}
