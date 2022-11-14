package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ArrangementAddNewDTO implements Serializable {

    //电影id
    private Long filmId;

    //影院名
    private String cinemaName;

    //影厅名
    private String screenName;

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
}
