package cn.tedu.galaxy.admin.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class RoleTests {
    @Autowired
    private RoleMapper mapper;

    @Test
    void testCountById() {
        Long id = 1L;

        int count = mapper.countById(id);
        log.debug("根据id（{}）进行统计，统计结果={}", id, count);
    }

    @Test
    void testList() {
        List<?> list = mapper.list();
        log.debug("查询列表完成，结果集的数量={}", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
    }
}
