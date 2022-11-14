package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Comment;
import cn.tedu.galaxy.commons.pojo.film.vo.CommentListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.CommentStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper {
    /**
     * 插入评论数据
     *
     * @param comment 评论数据
     * @return 受影响的行数
     */
    int insert(Comment comment);

    /**
     * 根据id删除评论
     *
     * @param id 被删除的评论的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改评论数据详情
     *
     * @param comment 封装了id与新数据的评论对象
     * @return 受影响的行数
     */
    int updateById(Comment comment);

    /**
     * 统计当前表中评论数据的数量
     *
     * @return 当前表中评论数据的数量
     */
    int count();


    /**
     * 根据id获取评论的标准信息
     *
     * @param id 评论id
     * @return 返回匹配的评论的标准信息，如果没有匹配的数据，将返回null
     */
    CommentStandardVO getStandardById(Long id);

    /**
     * 查询评论列表
     *
     * @return 评论列表
     */
    List<CommentListItemVO> listByFid(Long fid);
}
