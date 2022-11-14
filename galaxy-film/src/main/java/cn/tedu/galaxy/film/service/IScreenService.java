package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.ScreenAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ScreenUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenListItemVO;

import java.util.List;

public interface IScreenService {
    //新增影厅
    void save(ScreenAddNewDTO screenAddNewDTO);

    //根据id删除影厅
    void deleteById(Long id);

    //根据id修改影厅
    void updateById(Long id, ScreenUpdateDTO screenUpdateDTO);

    //查询影厅列表
    List<ScreenListItemVO> list();

    //根据影院名获取ro
    Integer getRo(String name);

    //根据影院名获取co
    Integer getCo(String name);
}
