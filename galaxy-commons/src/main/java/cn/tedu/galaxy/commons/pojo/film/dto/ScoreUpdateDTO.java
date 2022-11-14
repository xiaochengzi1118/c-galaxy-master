package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ScoreUpdateDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long filmId;

    private BigDecimal score;
}
