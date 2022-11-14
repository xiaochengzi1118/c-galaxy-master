package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ActorAddNewDTO implements Serializable {
    private Long id;

    private String name;

    private String photo;

}
