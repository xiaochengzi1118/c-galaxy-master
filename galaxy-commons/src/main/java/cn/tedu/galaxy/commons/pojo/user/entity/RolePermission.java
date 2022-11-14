package cn.tedu.galaxy.commons.pojo.user.entity;

import lombok.Data;

import java.io.Serializable;
import java.lang.ref.PhantomReference;
import java.time.LocalDateTime;

@Data
public class RolePermission implements Serializable {
    private Long id;
    private Long roleId;
    private Long permissionId;
    private LocalDateTime gmtCreate;
    private LocalDateTime gmtModified;
}
