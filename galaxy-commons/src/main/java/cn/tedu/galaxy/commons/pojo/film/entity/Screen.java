package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Screen implements Serializable {

    private Long id;

    private String name;

    private Integer typeId;

    private String type;

    private Long cinemaId;

    private String cinemaName;

    private Integer ro;

    private Integer co;
    //创建时间
    private LocalDateTime gmtCreate;
    //修改时间
    private LocalDateTime gmtModified;
}
