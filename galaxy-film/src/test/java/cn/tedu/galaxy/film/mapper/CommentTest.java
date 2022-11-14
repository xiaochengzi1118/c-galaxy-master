package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Comment;
import cn.tedu.galaxy.commons.pojo.film.vo.CommentListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CommentStandardVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class CommentTest {

    @Autowired
    CommentMapper mapper;

    @Test
    public void testInsert() {
        Comment comment = new Comment();
        comment.setContent("谢顶后的山峰");

        log.info("插入数据之前，参数={}", comment);
        int rows = mapper.insert(comment);
        log.info("rows = {}", rows);
        log.info("插入数据之后，参数={}", comment);
    }

    @Test
    public void testDeleteById(){
        Long id = 3L;
        int rows = mapper.deleteById(id);
        log.info("删除完成，受影响的行数={}", rows);
    }

    @Test
    public void testUpdateById() {
        Comment comment = new Comment();
        comment.setId(2L);
        comment.setContent("我的小弟之顺峰");

        int rows = mapper.updateById(comment);
        log.debug("修改相册信息完成，受影响的行数={}", rows);
    }

    @Test
    public void testCount(){
        int rows = mapper.count();
        log.info("当前表中电影数量={}",rows);
    }


    @Test
    public void testGetStandardById(){
        Long id = 3L;
        CommentStandardVO standardVO = mapper.getStandardById(id);
        log.info("根据id={}查询到的电影详情为:{}",id,standardVO);
    }

    @Test
    public void testList(){
        List<CommentListItemVO> list = mapper.listByFid(1L);
        log.debug("查询电影列表，查询结果中的数据的数量：{}", list.size());
        for (CommentListItemVO film:list) {
            log.info("{}",film);
        }
    }


}
