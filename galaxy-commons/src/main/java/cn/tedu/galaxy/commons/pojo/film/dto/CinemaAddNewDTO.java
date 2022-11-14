package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CinemaAddNewDTO implements Serializable {

    private String name;

    private String brand;
    //位置
    private String location;
}
