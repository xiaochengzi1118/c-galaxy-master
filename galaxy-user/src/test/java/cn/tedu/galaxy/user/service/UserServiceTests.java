package cn.tedu.galaxy.user.service;


import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.user.dto.UserUpdateDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class UserServiceTests {

    @Autowired
   IUserService userService;



    @Test
    void testUpdateInfoById() {
        Long id = 7L;
        UserUpdateDTO userUpdateDTO =new UserUpdateDTO();
        userUpdateDTO.setUsername("王五");
        userUpdateDTO.setPassword("66666666666");

        try {
            userService.updateById(id, userUpdateDTO);
            log.debug("修改管理员密码成功！");
        } catch (ServiceException e) {
            log.debug(e.getMessage());
        }
    }
}
