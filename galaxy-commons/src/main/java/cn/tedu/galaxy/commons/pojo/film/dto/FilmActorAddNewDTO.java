package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmActorAddNewDTO implements Serializable {

    private Long filmId;

    //上映时间
    private Long actorId;

}
