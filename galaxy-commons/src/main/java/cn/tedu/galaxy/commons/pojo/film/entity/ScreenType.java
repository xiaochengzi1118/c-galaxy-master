package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScreenType {

    private Long id;
    //类型名
    private String name;
    //创建时间
    private LocalDateTime gmt_create;
    //修改时间
    private LocalDateTime gmt_modified;
}
