package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.CinemaBrandAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.CinemaBrandUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaBrandListItemVO;

import java.util.List;

public interface ICinemaBrandService {
    //新增影院品牌
    void save(CinemaBrandAddNewDTO cinemaBrandAddNewDTO);

    //根据id删除影院品牌
    void deleteById(Long id);

    //根据id修改影院品牌
    void updateById(Long id, CinemaBrandUpdateDTO cinemaBrandUpdateDTO);

    //查询影院品牌列表
    List<CinemaBrandListItemVO> list();
}
