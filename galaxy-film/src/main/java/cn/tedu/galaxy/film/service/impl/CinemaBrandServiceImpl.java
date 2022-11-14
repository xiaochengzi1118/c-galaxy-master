package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.pojo.film.dto.CinemaBrandAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.CinemaBrandUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.CinemaBrand;
import cn.tedu.galaxy.commons.pojo.film.vo.CinemaBrandListItemVO;
import cn.tedu.galaxy.film.mapper.CinemaBrandMapper;
import cn.tedu.galaxy.film.service.ICinemaBrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CinemaBrandServiceImpl implements ICinemaBrandService {
    @Autowired
    private CinemaBrandMapper cinemaBrandMapper;

    @Override
    public void save(CinemaBrandAddNewDTO cinemaBrandAddNewDTO) {
        log.info("开始处理添加影院品牌的业务!");
        CinemaBrand cinemaBrand = new CinemaBrand();
        BeanUtils.copyProperties(cinemaBrandAddNewDTO, cinemaBrand);
        cinemaBrandMapper.insert(cinemaBrand);
        log.info("添加影院品牌完成！");
    }

    @Override
    public void deleteById(Long id) {
        cinemaBrandMapper.deleteById(id);
    }

    @Override
    public void updateById(Long id, CinemaBrandUpdateDTO cinemaBrandUpdateDTO) {
        CinemaBrand cinemaBrand = new CinemaBrand();
        BeanUtils.copyProperties(cinemaBrandUpdateDTO, cinemaBrand);
        cinemaBrand.setId(id);
        cinemaBrandMapper.insert(cinemaBrand);
        log.info("更新影院品牌完成！");
    }

    @Override
    public List<CinemaBrandListItemVO> list() {
        return cinemaBrandMapper.list();
    }
}
