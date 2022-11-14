package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 电影
 */
@Data
public class Actor implements Serializable {

    private Long id;

    private String name;

    private String photo;

    //电影时长 单位：分钟
//    private Integer duration;
    //创建时间
    private LocalDateTime gmtCreate;

    //修改时间
    private LocalDateTime gmtModified;
}
