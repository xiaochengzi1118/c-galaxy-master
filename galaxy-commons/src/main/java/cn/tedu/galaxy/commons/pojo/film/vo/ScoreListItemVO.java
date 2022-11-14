package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ScoreListItemVO implements Serializable {

    private Long id;

    private Long userId;

    private Long filmId;

    private BigDecimal score;
}
