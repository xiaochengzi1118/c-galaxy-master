package cn.tedu.galaxy.admin.mapper;

import cn.tedu.galaxy.commons.pojo.admin.entity.AdminRole;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
public class AdminRoleTests {
    @Autowired
    AdminRoleMapper mapper;

    @Test
    void testInsertBatch() {
        LocalDateTime now = LocalDateTime.now();
        AdminRole adminRole = new AdminRole();
        adminRole.setUserId(5L);
        adminRole.setRoleId(1L);
        adminRole.setGmtCreate(now);
        adminRole.setGmtModified(now);

        int rows = mapper.insertBatch(adminRole);
        log.debug("批量插入管理员与角色的关联关系数据成功，受影响的行数={}", rows);
    }

    @Test
    void testDeleteByAdmin() {
        Long adminId = 5L;
        int rows = mapper.deleteByAdmin(adminId);
        log.debug("根据管理员【id={}】删除关联数据，受影响的行数={}", adminId, rows);
    }

}
