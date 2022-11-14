package cn.tedu.galaxy.commons.pojo.admin.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminAddNewDTO implements Serializable {
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
     * 性别，0男，1女
     */
    private Integer sex;
    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 是否启用，1=启用，0=未启用
     */
    private Integer enable;

}
