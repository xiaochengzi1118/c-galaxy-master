package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RemainingReduceCountDTO implements Serializable {

    //场次id
    private Long id;

    //减少库存数
    private Integer reduceCount;

}
