package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;

import java.io.Serializable;

//影厅座位
@Data
public class ScreenSeatListItemVO implements Serializable {

    private Long id;

    private Integer screenId;

    private Integer ro;

    private Integer co;
}
