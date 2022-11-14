package cn.tedu.galaxy.admin.service;

import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminAddNewDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@Slf4j
public class AdminServiceTests {
    @Autowired
    IAdminService service;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testPassword(){
        String p = passwordEncoder.encode("123456");
        System.out.println(p);
    }

    @Test
    void testAddNew(){
        AdminAddNewDTO admin = new AdminAddNewDTO();
        admin.setUsername("test2");
        admin.setPassword("189");
        admin.setPhone("987");
        admin.setEmail("baidu.com");

        try {
            service.addNew(admin);
            log.debug("添加管理员成功！");
        } catch (ServiceException e) {
            log.debug("添加管理员失败！");
            log.debug("业务状态码：{}", e.getServiceCode());
            log.debug("错误信息：{}", e.getMessage());
        }
    }
}
