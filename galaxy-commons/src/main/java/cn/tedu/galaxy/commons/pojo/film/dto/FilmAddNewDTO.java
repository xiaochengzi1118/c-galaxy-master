package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FilmAddNewDTO implements Serializable {

    private String name;

    private String actor;

    // 电影导演
    private String director;

    //详情
    private String detail;

    //电影时常
    private String duration;

    //类型
    private String type;

    //热度
    private Integer hot;

    //上映时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtRelease;

    //地区 中国,美国,韩国 ......
    private String region;

    //电影封面
    private String picture;

    //状态
    private Integer state;
}
