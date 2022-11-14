package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FilmListByCinemaIdVO implements Serializable {

    //电影id
    private Long id;

    private String name;

    private String picture;    //电影海报地址

}
