package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.ScreenTypeAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ScreenTypeUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenTypeListItemVO;

import java.util.List;

public interface IScreenTypeService {
    //新增影厅类型
    void save(ScreenTypeAddNewDTO screenTypeAddNewDTO);

    //根据id删除影厅类型
    void deleteById(Long id);

    //根据id修改影厅类型
    void updateById(Long id, ScreenTypeUpdateDTO screenTypeUpdateDTO);

    //查询影厅类型列表
    List<ScreenTypeListItemVO> list();
}
