package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 电影
 */
@Data
public class ArrangementListByFilmIdVO implements Serializable {

    private Long id;

    //影厅名
    private String screenName;

    //影厅类型
    private String type;

    //价格
    private BigDecimal price;

    //开始时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtStart;

    //结束时间
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtEnd;
}
