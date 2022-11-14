package cn.tedu.galaxy.commons.pojo.user.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserListItemVO {
    private Long id;
    private String username;
    private String password;
    private String nick;
    private Integer sex;
    private String avatar;
    private String email;
    private String phone;
    private Integer enable;
    private String last_login_ip;
    private String login_count;
    private LocalDateTime gmt_last_login;
    private LocalDateTime gmt_create;
    private LocalDateTime gmt_modified;
}
