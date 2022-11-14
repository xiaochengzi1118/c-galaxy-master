package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 电影
 */
@Data
//无参构造器
@NoArgsConstructor
//有参构造器
@AllArgsConstructor
public class Film implements Serializable {

    private Long id;


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

    //创建时间
    private LocalDateTime gmtCreate;

    //修改时间
    private LocalDateTime gmtModified;

}
