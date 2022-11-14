package cn.tedu.galaxy.commons.pojo.admin.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AdminStandardVO implements Serializable {
    /**
     * 数据id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码（原始密码）
     */
    private String password;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 性别，1男，2女，0未知
     */
    private Integer sex;
    /**
     * 头像URL
     */
    private String avatar;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;
    /**
     * 最后登录IP地址
     */
    private String lastLoginIp;
    /**
     * 累计登录次数（冗余）
     */
    private Integer loginCount;
    /**
     * 最后登录时间（冗余）
     */
    private LocalDateTime gmtLastLogin;

}
