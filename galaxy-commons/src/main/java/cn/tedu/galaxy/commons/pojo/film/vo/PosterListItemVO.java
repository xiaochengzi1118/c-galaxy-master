package cn.tedu.galaxy.commons.pojo.film.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class PosterListItemVO implements Serializable {
    private Long id;
    private String title;
    private String url;
    private int status;
    private LocalDateTime createAt;
}
