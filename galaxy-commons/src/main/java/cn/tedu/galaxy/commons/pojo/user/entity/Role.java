package cn.tedu.galaxy.commons.pojo.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Role implements Serializable {
    private Long id;
    private String name;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
