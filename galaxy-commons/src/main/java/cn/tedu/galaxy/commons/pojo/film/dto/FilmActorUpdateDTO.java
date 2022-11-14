package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FilmActorUpdateDTO implements Serializable {
    private Long id;

    private Long filmId;

    //上映时间
    private Long actorId;
}
