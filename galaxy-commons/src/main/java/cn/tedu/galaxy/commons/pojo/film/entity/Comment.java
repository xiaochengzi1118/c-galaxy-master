package cn.tedu.galaxy.commons.pojo.film.entity;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 电影
 */
@Data
public class Comment implements Serializable {

    private Long id;

    private Long userId;

    private String username;

    private String nick;

    //头像
    private String avatar;

    //内容
    private String content;

    //电影id
    private Long filmId;

    //电影名
    private String filmName;

    //点赞数
    private Integer likeCount;

    //评论数
    private Integer commentCount;

    //深度
    private Integer depth;

    //父级评论
    private Long parentId;

    //评论时间
    private LocalDateTime gmtComment;

    //创建时间
    private LocalDateTime gmtCreate;

    //修改时间
    private LocalDateTime gmtModified;

}
