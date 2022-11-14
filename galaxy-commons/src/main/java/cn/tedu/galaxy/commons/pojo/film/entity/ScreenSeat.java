package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//影厅座位
@Data
public class ScreenSeat implements Serializable {

    private Long id;

    private Integer screenId;

    private Integer ro;

    private Integer co;
    //创建时间
    private LocalDateTime gmtCreate;
    //修改时间
    private LocalDateTime gmtModified;
}
