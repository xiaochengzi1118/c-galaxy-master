package cn.tedu.galaxy.admin.mapper;

import cn.tedu.galaxy.commons.pojo.admin.entity.Admin;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminListItemVO;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class AdminMapperTests {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    void testInsert(){
        Admin admin = new Admin();
        admin.setUsername("吹气球");
        admin.setPassword("1212");
        admin.setPhone("134546");
        admin.setEmail("16465.com");

        log.debug("插入数据之前，参数：{}", admin);
        int rows = adminMapper.insert(admin);
        log.debug("插入数据完成，受影响的行数：{}", rows);
        log.debug("插入数据之后，参数：{}", admin);
    }

    @Test
    void testDeleteById() {
        Long id = 4L;
        int rows = adminMapper.deleteById(id);
        log.debug("根据id={}删除管理员成功，受影响的行数={}", id, rows);
    }

    @Test
    void testUpdate() {
        Admin admin = new Admin();
        admin.setId(2L);
        admin.setEnable(1);
        admin.setEmail("admin2@qq.com");

        int rows = adminMapper.update(admin);
        log.debug("修改数据完成，受影响的行数={}", rows);
    }

    @Test
    void testCountByUsername() {
        String username = "张三";
        int count = adminMapper.countByUsername(username);
        log.debug("根据用户名【{}】统计管理员账号的数量：{}", username, count);
    }

    @Test
    void testCountByPhone() {
        String phone = "1212";
        int count = adminMapper.countByPhone(phone);
        log.debug("根据手机号码【{}】统计管理员账号的数量：{}", phone, count);
    }

    @Test
    void testCountByEmail() {
        String email = "admin2@qq.com";
        int count = adminMapper.countByEmail(email);
        log.debug("根据电子邮箱【{}】统计管理员账号的数量：{}", email, count);
    }

    @Test
    void testGetStandardById() {
        Long id = 2L;
        Object queryResult = adminMapper.getStandardById(id);
        log.debug("根据id={}查询管理员详情，查询结果={}", id, queryResult);
    }


    @Test
    void testList() {
        List<AdminListItemVO> adminList = adminMapper.list();
        log.debug("查询管理员列表，结果集中的数据的数量={}", adminList.size());
        for (AdminListItemVO admin : adminList) {
            log.debug("{}", admin);
        }
    }

    @Test
    void testGetLoginInfoByUsername() {
        String username = "张三";
        AdminLoginInfoVO loginInfoByUsername = adminMapper.getLoginInfoByUsername(username);
        log.debug("根据用户名【{}】查询登录信息：{}", username, loginInfoByUsername);
    }
}
