package cn.tedu.galaxy.user.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * 管理员详情，专用于Spring Security
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public class UsersDetails extends User {

    private Long id;

    private String phone;
    public UsersDetails(String username, String password, boolean enabled,
                        Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled,true, true, true, authorities);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "AdminDetails{" +
                "id=" + id +
                ", " + super.toString() +
                '}';
    }

}
