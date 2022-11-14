package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 电影
 */
@Data
public class FilmActor implements Serializable {

    private Long id;

    private Long filmId;

    //上映时间
    private Long actorId;

    //创建时间
    private LocalDateTime gmtCreate;

    //修改时间
    private LocalDateTime gmtModified;
}
