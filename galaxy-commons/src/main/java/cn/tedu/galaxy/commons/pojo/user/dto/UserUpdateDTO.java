package cn.tedu.galaxy.commons.pojo.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserUpdateDTO implements Serializable {


    private String username;


    private String password;

    private String nick;
    /**
     * 性别
     */
    private Integer sex;
    /**
     * 头像
     */
    private String avatar;

    private String email;

    private String phone;

    private Integer enable;
}
