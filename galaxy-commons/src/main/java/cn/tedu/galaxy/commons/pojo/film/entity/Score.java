package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Score implements Serializable {

    private Long id;

    private Long userId;

    private Long filmId;

    private BigDecimal score;
    //创建时间
    private LocalDateTime gmtCreate;
    //修改时间
    private LocalDateTime gmtModified;
}
