package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 电影
 */
@Data
public class Order implements Serializable {

    private Long id;

    //场次id
    private Long arrangementId;

    //电影名
    private String filmName;

    //影院名
    private String cinemaName;

    //影厅名
    private String screenName;

    //影厅类型
    private String screenType;

    //封面
    private String picture;

    //价格
    private BigDecimal price;

    private Long userId;

    private String username;

    private String phone;

    //行数
    private Integer ro;

    //列数
    private Integer co;

    //状态
    private Integer state;

    //开始时间
    private LocalDateTime gmtStart;

    //结束时间
    private LocalDateTime gmtEnd;

    //下单时间
    private LocalDateTime gmtOrder;

    //支付时间
    private LocalDateTime gmtPay;

    //创建时间
    private LocalDateTime gmtCreate;

    //修改时间
    private LocalDateTime gmtModified;
}
