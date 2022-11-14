package cn.tedu.galaxy.commons.pojo.admin.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AdminLoginInfoDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（原密码）
     */
    private String password;

}
