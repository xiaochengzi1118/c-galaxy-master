package cn.tedu.galaxy.commons.pojo.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserRole implements Serializable {
    private Long id;
    private Long userId;
    private Long roleId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
