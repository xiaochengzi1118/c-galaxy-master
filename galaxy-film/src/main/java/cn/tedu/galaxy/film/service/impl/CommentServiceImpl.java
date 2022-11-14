package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.CommentAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.CommentUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Comment;
import cn.tedu.galaxy.commons.pojo.film.entity.Score;
import cn.tedu.galaxy.commons.pojo.film.vo.CommentListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ScoreStandardVO;
import cn.tedu.galaxy.film.mapper.CommentMapper;
import cn.tedu.galaxy.film.mapper.ScoreMapper;
import cn.tedu.galaxy.film.service.ICommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private CommentMapper commentMapper;


    @Override
    public void save(CommentAddNewDTO commentAddNewDTO) {
        log.debug("【添加电影评论】业务");
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentAddNewDTO,comment);
        comment.setGmtComment(LocalDateTime.now());
        commentMapper.insert(comment);

        if (commentAddNewDTO.getScore()!=null){
            log.debug("【添加电影评分】业务");
            Long fid = commentAddNewDTO.getFilmId();
            Long uid = commentAddNewDTO.getUserId();
            Score score = new Score();
            score.setUserId(uid);
            score.setFilmId(fid);
            score.setScore(commentAddNewDTO.getScore());

            ScoreStandardVO queryResult = scoreMapper.getStandardByFidAndUid(fid, uid);
            if (queryResult!=null){
                int rows = scoreMapper.updateById(score);
                if (rows!=1){
                    String message = "修改评分失败";
                    log.debug(message);
                    throw new ServiceException(ServiceCode.ERR_UPDATE, message);
                }
            }else {
                scoreMapper.insert(score);
            }
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void updateById(Long id, CommentUpdateDTO commentUpdateDTO) {

    }

    @Override
    public List<CommentListItemVO> listByFid(Long fid) {
        log.debug("正在[处理查询当前电影评论]业务");
        List<CommentListItemVO> result = commentMapper.listByFid(fid);
        return result;
    }


}
