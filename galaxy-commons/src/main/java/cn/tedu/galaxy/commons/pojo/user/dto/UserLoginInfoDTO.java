package cn.tedu.galaxy.commons.pojo.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginInfoDTO implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码（原密码）
     */
    private String password;
}
