package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 电影地区
 */
@Data
public class Region implements Serializable {

    private Long id;

    private String name;

    //创建时间
    private LocalDateTime gmtCreate;

    //修改时间
    private LocalDateTime gmtModified;
}
