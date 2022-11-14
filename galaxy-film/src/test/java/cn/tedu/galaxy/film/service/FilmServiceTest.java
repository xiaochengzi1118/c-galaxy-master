package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.FilmAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Film;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListItemVO;
import cn.tedu.galaxy.film.service.impl.FilmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FilmServiceTest {
    @Autowired
    FilmServiceImpl filmService;

    @Test
    public void saveTest(){
        FilmAddNewDTO filmAddNewDTO = new FilmAddNewDTO();
        filmAddNewDTO.setName("11111");
        filmAddNewDTO.setHot(99);
        log.debug("添加中。。");
        filmService.save(filmAddNewDTO);
        log.debug("添加成功！");
    }

    @Test
    public void testListBy(){
        String type="全部";
        String region="美国";
        String year="全部";
        List<FilmListItemVO> list = filmService.listBy(type,region,year);
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (FilmListItemVO film:list) {
            log.info("{}",film);
        }
    }

    @Test
    public void testState(){
        List<FilmListItemVO> allFilm = filmService.findAllFilm(0);
        allFilm.forEach(film -> System.out.println(film));
    }
}
