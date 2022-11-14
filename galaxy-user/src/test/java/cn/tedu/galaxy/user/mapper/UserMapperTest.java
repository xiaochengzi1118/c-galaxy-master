package cn.tedu.galaxy.user.mapper;

import cn.tedu.galaxy.commons.pojo.user.entity.User;
import cn.tedu.galaxy.commons.pojo.user.vo.UserListItemVO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserMapper mapper;

    @Test
    void add(){
        User user= new User();
        user.setUsername("王六");
        user.setPassword("1213456");
        user.setEmail("1121351");
        user.setEmail("1231215445646");
        log.debug("插入数据之前，参数：{}", user);
        int rows = mapper.insert(user);
        log.debug("插入数据完成，受影响的行数：{}", rows);
        log.debug("插入数据之后，参数：{}", user);
    }
    @Test
    void testDeleteById() {
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.debug("根据id={}删除用户成功，受影响的行数={}", id, rows);
    }
    @Test
    public void testDeleteByIds() {
        Long[] ids = {3L, 5L, 6L, 9L};
        int rows = mapper.deleteByIds(ids);
        log.info("批量删除完成，受影响的行数={}", rows);
    }
    @Test
    void testUpdate() {
        User user= new User();
        user.setUsername("叮当");
        user.setId(4L);
        user.setEnable(1);
        user.setAvatar("http://www.baidu.com/avatar.png");
        user.setEmail("admin2@qq.com");

        int rows = mapper.update(user);
        log.debug("修改数据完成，受影响的行数={}", rows);
    }
    @Test
    void testCountByUsername() {
        String username = "王六";
        int count = mapper.countByUsername(username);
        log.debug("根据用户名【{}】统计用户名账号的数量：{}", username, count);
    }
    @Test
    void testCountByPhone() {
        String phone = "1212";
        int count = mapper.countByPhone(phone);
        log.debug("根据手机号码【{}】统计用户名账号的数量：{}", phone, count);
    }

    @Test
    void testCountByEmail() {
        String email = "123@qq.com";
        int count = mapper.countByEmail(email);
        log.debug("根据电子邮箱【{}】统计用户名账号的数量：{}", email, count);
    }
    @Test
    void testGetStandardById() {
        Long id = 6L;
        Object queryResult = mapper.getStandardById(id);
        log.debug("根据id={}查询用户名详情，查询结果={}", id, queryResult);
    }
    @Test
    void testList() {
        List<UserListItemVO> userList = mapper.list();
        log.debug("查询用户名列表，结果集中的数据的数量={}", userList.size());
        for (UserListItemVO user : userList) {
            log.debug("{}", user);
        }
    }
    @Test
    void testGetLoginInfoByUsername() {
        String username = "张三";
        UserLoginInfoVO loginInfoByUsername = mapper.getLoginInfoByUsername(username);
        log.debug("根据用户名【{}】查询登录信息：{}", username, loginInfoByUsername);
    }
    @Test
    void testGetStandardByPhone() {

        Object queryResult = mapper.getStandardByPhone("15860166127");
        log.debug("查询结果={}",  queryResult);
    }


}
