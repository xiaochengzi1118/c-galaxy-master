package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FilmListItemVO implements Serializable {
    private Long id;

    private String name;

    private String actor;     //演职人员
    private String director;  //导演
    private String detail;    //电影详情
    private String duration;  //电影时长
    private String type;        //电影类型
    private Integer hot;      //电影评分
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime gmtRelease;        //上映时间
    private String region;    //制片地区
    private String picture;    //电影海报地址
    private int state;         //电影状态 默认1 1：在线 0：下架
//    private List<Comment> commentList; //所有的评论信息
}
