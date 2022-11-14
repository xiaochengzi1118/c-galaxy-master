package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.vo.PosterListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
@Slf4j
@SpringBootTest
public class PostTest {
    @Autowired
    PosterMapper mapper;

    @Test
    public void testList(){
        List<PosterListItemVO> list = mapper.list();
        for (PosterListItemVO vo : list) {
            log.debug("{}",vo);
        }
    }
}
