package cn.tedu.galaxy.commons.pojo.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String nick;
    private Integer sex;
    private String avatar;
    private String email;
    private String phone;
    private Integer enable;
    private String lastLoginIp;
    private String loginCount;
    private LocalDateTime gmtLastLogin;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
