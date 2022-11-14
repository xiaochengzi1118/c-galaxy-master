package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.ArrangementAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmAddNewDTO;
import cn.tedu.galaxy.film.mapper.ArrangementMapper;
import cn.tedu.galaxy.film.service.impl.FilmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ArrangementServiceTest {
    @Autowired
    private IArrangementService arrangementService ;

    @Test
    void testSave(){
        ArrangementAddNewDTO arrangementAddNewDTO = new ArrangementAddNewDTO();
        //arrangementAddNewDTO.setGmtStart();
        arrangementService.save(arrangementAddNewDTO);
    }
}
