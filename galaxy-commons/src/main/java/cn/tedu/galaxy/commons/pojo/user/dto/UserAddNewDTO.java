package cn.tedu.galaxy.commons.pojo.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserAddNewDTO implements Serializable {

    @ApiModelProperty(value = "用户名名称", required = true)
    @NotNull(message = "添加用户名失败！必须提交用户名名称！")
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotNull(message = "添加密码失败！必须提交密码！")
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
    @ApiModelProperty(value = "手机号码", required = true)
    @NotNull(message = "添加手机号码失败！必须提交手机号码！")
    private String phone;

    private Integer enable;
}
