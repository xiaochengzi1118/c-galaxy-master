package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.ScoreAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Comment;
import cn.tedu.galaxy.commons.pojo.film.entity.Score;
import cn.tedu.galaxy.commons.pojo.film.vo.ScoreStandardVO;
import cn.tedu.galaxy.film.mapper.CommentMapper;
import cn.tedu.galaxy.film.mapper.ScoreMapper;
import cn.tedu.galaxy.film.service.IScoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@Slf4j
public class ScoreServiceImpl implements IScoreService {
    @Autowired
    private ScoreMapper scoreMapper;

    @Override
    public BigDecimal getScoreByFilmId(Long id) {
        log.debug("【根据电影id查询电影评分】业务");
        BigDecimal result = scoreMapper.getScoreByFilmId(id);
        if(result==null){
            result=BigDecimal.valueOf(5);
        }
        return result;

    }
}
