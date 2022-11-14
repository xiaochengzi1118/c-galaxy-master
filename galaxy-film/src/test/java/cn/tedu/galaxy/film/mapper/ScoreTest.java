package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Score;
import cn.tedu.galaxy.commons.pojo.film.vo.ScoreListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScoreStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Slf4j
public class ScoreTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    public void testInsert() {
        Score score = new Score();
        score.setScore(BigDecimal.valueOf(4));

        log.info("插入数据之前，参数={}", score);
        int rows = mapper.insert(score);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", score);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Score score = new Score();
        score.setId(2L);
        score.setScore(BigDecimal.valueOf(8));

        int rows = mapper.updateById(score);
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
        ScoreStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<ScoreListItemVO> list = mapper.list();
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (ScoreListItemVO film:list) {
            log.info("{}",film);
        }
    }


}
