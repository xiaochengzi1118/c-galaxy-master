package cn.tedu.galaxy.admin.service.impl;

import cn.tedu.galaxy.admin.mapper.RoleMapper;
import cn.tedu.galaxy.admin.service.IRoleService;
import cn.tedu.galaxy.commons.pojo.admin.vo.RoleListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<RoleListItemVO> list() {
        log.debug("开始处理【查询角色列表】的业务");
        return roleMapper.list();
    }

}
