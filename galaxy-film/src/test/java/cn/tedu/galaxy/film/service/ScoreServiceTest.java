package cn.tedu.galaxy.film.service;


import cn.tedu.galaxy.commons.pojo.film.vo.ScoreStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
public class ScoreServiceTest {
    @Autowired
    IScoreService scoreService;

    @Test
    public void testScore(){
        BigDecimal result = scoreService.getScoreByFilmId(2L);
        log.debug(result.toString());
    }


}
