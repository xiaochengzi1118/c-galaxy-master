package cn.tedu.galaxy.user.mapper;

import cn.tedu.galaxy.commons.pojo.user.entity.User;
import cn.tedu.galaxy.commons.pojo.user.entity.UserRole;
import cn.tedu.galaxy.commons.pojo.user.vo.UserListItemVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class UserRoleMapperTest {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Test
    void addNew(){
        UserRole userRole =new UserRole();
        userRole.setRoleId(2L);
        int rows = userRoleMapper.insertBatch(userRole);
        log.debug("批量插入管理员与角色的关联关系数据成功，受影响的行数={}", rows);
    }
    @Test
    void testDeleteByAdmin() {
        Long userId = 8L;
        int rows = userRoleMapper.deleteByUser(userId);
        log.debug("根据管理员【id={}】删除关联数据，受影响的行数={}", userId, rows);
    }

}
