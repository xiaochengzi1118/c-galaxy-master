package cn.tedu.galaxy.admin.security;

import cn.tedu.galaxy.admin.mapper.AdminMapper;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security自动调用loadUserByUsername()方法获取用户名为【{}】的用户详情……", s);

        AdminLoginInfoVO loginAdmin = adminMapper.getLoginInfoByUsername(s);
        log.debug("从数据库中查询到的用户信息：{}", loginAdmin);
        if (loginAdmin == null) {
            String message = "登录失败，用户名不存在！";
            log.warn(message);
            throw new BadCredentialsException(message);
        }

        List<String> permissions = loginAdmin.getPermissions();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }

        AdminDetails adminDetails = new AdminDetails(
                loginAdmin.getUsername(), loginAdmin.getPassword(),
                loginAdmin.getEnable() == 1, authorities);
        adminDetails.setId(loginAdmin.getId());

        log.debug("即将向Spring Security框架返回UserDetails对象：{}", adminDetails);
        return adminDetails;
    }
}
