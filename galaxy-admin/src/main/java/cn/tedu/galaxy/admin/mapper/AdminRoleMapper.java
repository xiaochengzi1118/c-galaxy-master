package cn.tedu.galaxy.admin.mapper;

import cn.tedu.galaxy.commons.pojo.admin.entity.AdminRole;
import org.springframework.stereotype.Repository;

/**
 * 处理管理员与角色关联数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface AdminRoleMapper {

    /**
     * 批量插入管理员与角色的关联数据
     *
     * @param adminRole 管理员与角色的关联数据
     * @return 受影响的行数
     */
    int insertBatch(AdminRole adminRole);

    /**
     * 根据管理员删除管理员与角色的关联数据
     *
     * @param userId 管理员id
     * @return 受影响的行数
     */
    int deleteByAdmin(Long userId);

}
