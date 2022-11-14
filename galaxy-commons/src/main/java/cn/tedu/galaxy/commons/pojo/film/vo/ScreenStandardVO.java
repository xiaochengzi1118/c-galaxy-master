package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ScreenStandardVO implements Serializable {

    private Long id;

    private String name;

    private Integer typeId;

    private String type;

    private Long cinemaId;

    private String cinemaName;

    private Integer ro;

    private Integer co;

}
