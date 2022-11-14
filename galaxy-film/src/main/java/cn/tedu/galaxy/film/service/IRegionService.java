package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.RegionAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.RegionUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.RegionListItemVO;

import java.util.List;

public interface IRegionService {
    //新增电影地区
    void save(RegionAddNewDTO regionAddNewDTO);

    //根据id删除电影地区
    void deleteById(Long id);

    //根据id修改电影地区
    void updateById(Long id, RegionUpdateDTO regionUpdateDTO);

    //查询电影地区列表
    List<RegionListItemVO> list();
}
