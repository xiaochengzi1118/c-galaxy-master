package cn.tedu.galaxy.commons.pojo.admin.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminUpdateInfoDTO implements Serializable {

    /**
     * 数据id
     */
    private Long id;

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

}
