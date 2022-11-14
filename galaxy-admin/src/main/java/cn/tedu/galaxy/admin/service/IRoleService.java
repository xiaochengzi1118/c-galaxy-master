package cn.tedu.galaxy.admin.service;

import cn.tedu.galaxy.commons.pojo.admin.vo.RoleListItemVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IRoleService {

    /**
     * 查询角色列表
     *
     * @return 角色列表
     */
    List<RoleListItemVO> list();
}
