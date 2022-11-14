package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScreenUpdateDTO implements Serializable {
    private String name;

    private Integer typeId;

    private String type;

    private Long cinemaId;

    private String cinemaName;

    private Integer ro;

    private Integer co;
}
