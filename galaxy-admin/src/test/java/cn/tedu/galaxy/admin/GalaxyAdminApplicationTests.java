package cn.tedu.galaxy.admin;


import cn.tedu.galaxy.commons.pojo.admin.entity.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class GalaxyAdminApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    void contextLoads() throws SQLException {
        dataSource.getConnection();
    }

    @Test
    void test(){
        Admin admin = new Admin();
        System.out.println(admin);
    }

}
