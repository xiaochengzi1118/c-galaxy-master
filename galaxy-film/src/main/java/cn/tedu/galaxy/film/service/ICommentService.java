package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.CommentAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.CommentUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.CommentListItemVO;

import java.util.List;

public interface ICommentService {
    //新增评论
    void save(CommentAddNewDTO commentAddNewDTO);

    //根据id删除评论
    void deleteById(Long id);

    //根据id修改评论
    void updateById(Long id, CommentUpdateDTO commentUpdateDTO);

    //查询评论列表
    List<CommentListItemVO> listByFid(Long fid);
}
