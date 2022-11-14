package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 电影
 */
@Data
public class Arrangement implements Serializable {

    private Long id;

    //电影id
    private Long filmId;

    private Long cinemaId;

    //影院名
    private String cinemaName;

    //影厅id
    private Long screenId;

    //影厅名
    private String screenName;

    //影厅类型
    private String screenType;

    //电影名
    private String filmName;

    //价格
    private BigDecimal price;

    //剩余座位数
    private Integer remaining;

    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtStart;

    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtEnd;

    //创建时间
    private LocalDateTime gmtCreate;

    //修改时间
    private LocalDateTime gmtModified;
}
