package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class CinemaBrand implements Serializable {

    private Long id;

    private String name;
    //创建时间
    private LocalDateTime gmtCreate;
    //修改时间
    private LocalDateTime gmtModified;
}
