package cn.tedu.galaxy.commons.pojo.film.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CommentAddNewDTO implements Serializable {
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

    private BigDecimal score;
}
