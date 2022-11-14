package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.pojo.film.dto.ScreenAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ScreenUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenListItemVO;
import cn.tedu.galaxy.film.mapper.ScreenMapper;
import cn.tedu.galaxy.film.service.IScreenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScreenServiceImpl implements IScreenService {

    @Autowired
    private ScreenMapper screenMapper;

    @Override
    public void save(ScreenAddNewDTO screenAddNewDTO) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id, ScreenUpdateDTO screenUpdateDTO) {

    }

    @Override
    public List<ScreenListItemVO> list() {
        return screenMapper.list();
    }

    @Override
    public Integer getRo(String name) {
        return screenMapper.getRoByName(name);
    }

    @Override
    public Integer getCo(String name) {
        return screenMapper.getCoByName(name);
    }

}
