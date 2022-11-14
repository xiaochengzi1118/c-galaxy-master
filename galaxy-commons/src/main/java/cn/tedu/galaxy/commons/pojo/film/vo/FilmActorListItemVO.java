package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 电影
 */
@Data
public class FilmActorListItemVO implements Serializable {

    private Long id;

    private Long filmId;

    //上映时间
    private Long actorId;

}
