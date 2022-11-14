package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.pojo.film.dto.RegionAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.RegionUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Region;
import cn.tedu.galaxy.commons.pojo.film.vo.RegionListItemVO;
import cn.tedu.galaxy.film.mapper.RegionMapper;
import cn.tedu.galaxy.film.service.IRegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RegionServiceImpl implements IRegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    public void save(RegionAddNewDTO regionAddNewDTO) {
        Region region = new Region();
        BeanUtils.copyProperties(regionAddNewDTO, region);
        regionMapper.insert(region);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id, RegionUpdateDTO regionUpdateDTO) {

    }

    @Override
    public List<RegionListItemVO> list() {
        return null;
    }
}
