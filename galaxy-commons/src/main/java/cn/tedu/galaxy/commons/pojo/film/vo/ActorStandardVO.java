package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 电影
 */
@Data
public class ActorStandardVO implements Serializable {

    private Long id;

    private String name;

    private String photo;

}
