package cn.tedu.galaxy.commons.pojo.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class UserNoteLoginInfoDTO implements Serializable {

   @ApiModelProperty(value = "手机号码", required = true)
   @NotNull(message = "添加手机号码失败！必须提交手机号码！")

   private String phone;
   @ApiModelProperty(value = "验证码", required = true)
   @NotNull(message = "添加验证码失败！必须提交验证码名称！")
   private String code;
}
