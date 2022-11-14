package cn.tedu.galaxy.commons.pojo.film.dto;

import cn.tedu.galaxy.commons.pojo.film.entity.Comment;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class ScoreAddNewDTO implements Serializable {

    private Long id;

    private Long userId;

    private Long filmId;

    private BigDecimal score;



    private String nick;
    private String username;
    //内容
    private String content;
}
