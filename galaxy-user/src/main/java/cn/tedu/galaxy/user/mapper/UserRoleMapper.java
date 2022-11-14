package cn.tedu.galaxy.user.mapper;

import cn.tedu.galaxy.commons.pojo.user.entity.UserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleMapper {
    /**
     * 批量插入管理员与角色的关联数据
     *
     * @param userRole 若干个管理员与角色的关联数据
     * @return 受影响的行数
     */
    int insertBatch(UserRole userRole);

    /**
     * 根据管理员删除管理员与角色的关联数据
     *
     * @param userId 管理员id
     * @return 受影响的行数
     */
    int deleteByUser(Long userId);
}
