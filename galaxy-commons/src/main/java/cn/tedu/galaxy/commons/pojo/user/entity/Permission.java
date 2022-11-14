package cn.tedu.galaxy.commons.pojo.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Permission implements Serializable {
    private Long id;
    private String description;
    private String value;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
