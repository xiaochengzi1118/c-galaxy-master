package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaStandardVO implements Serializable {

    private Long id;

    private String name;

    private String brand;
    //位置
    private String location;
    //是否可退票
    private Integer isRefuse;
    //是否可改签
    private Integer isEndorse;
}
