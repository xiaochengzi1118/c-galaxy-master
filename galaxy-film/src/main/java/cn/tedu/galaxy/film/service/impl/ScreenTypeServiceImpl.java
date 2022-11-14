package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.ScreenTypeAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ScreenTypeUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.ScreenType;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenTypeListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScreenTypeStandardVO;
import cn.tedu.galaxy.film.mapper.ScreenTypeMapper;
import cn.tedu.galaxy.film.service.IScreenTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ScreenTypeServiceImpl implements IScreenTypeService {

    @Autowired
    private ScreenTypeMapper screenTypeMapper;


    @Override
    public void save(ScreenTypeAddNewDTO screenTypeAddNewDTO) {
        String name = screenTypeAddNewDTO.getName();
        int count = screenTypeMapper.countByName(name);
        if (count > 0) {
            throw new ServiceException(ServiceCode.ERR_CONFLICT, "添加电影类型失败！电影名称【" + name + "】已存在");
        }
        ScreenType screenType = new ScreenType();
        BeanUtils.copyProperties(screenTypeAddNewDTO, screenType);
        int rows = screenTypeMapper.insert(screenType);
        if (rows == 0) {
            throw new ServiceException(ServiceCode.ERR_INSERT, "添加电影类型失败！服务器忙，请稍后再试");
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除电影类型】的业务，id:{}", id);
        ScreenTypeStandardVO queryResult = screenTypeMapper.getStandardById(id);
        if (queryResult == null) {
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, "删除电影类型失败，尝试访问电影类型不存在");
        }
        int rows = screenTypeMapper.deleteById(id);
        if (rows != 1) {
            throw new ServiceException(ServiceCode.ERR_DELETE, "删除电影类型失败，服务器忙，请稍后再次尝试！");
        }
    }

    @Override
    public void updateById(Long id, ScreenTypeUpdateDTO screenTypeUpdateDTO) {

    }

    @Override
    public List<ScreenTypeListItemVO> list() {
        return screenTypeMapper.list();
    }
}
