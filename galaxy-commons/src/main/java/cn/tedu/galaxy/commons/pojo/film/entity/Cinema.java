package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Cinema implements Serializable {

    private Long id;

    private String name;

    private String brand;
    //位置
    private String location;
    //是否可退票
    private Integer isRefuse;
    //是否可改签
    private Integer isEndorse;
    //创建时间
    private LocalDateTime gmtCreate;
    //修改时间
    private LocalDateTime gmtModified;
}
