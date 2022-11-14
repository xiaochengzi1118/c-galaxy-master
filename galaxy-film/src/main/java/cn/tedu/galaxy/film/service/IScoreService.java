package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.ScoreAddNewDTO;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
public interface IScoreService {
    BigDecimal getScoreByFilmId(Long id);
}
