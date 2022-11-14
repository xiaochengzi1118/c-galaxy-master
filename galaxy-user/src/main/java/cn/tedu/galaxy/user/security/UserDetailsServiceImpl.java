package cn.tedu.galaxy.user.security;


import cn.tedu.galaxy.commons.pojo.user.vo.UserLoginInfoVO;
import cn.tedu.galaxy.user.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户详情业务实现类，专用于Spring Security，将在Spring Security执行认证时自动调用
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.debug("Spring Security自动调用loadUserByUsername()方法获取用户名为【{}】的用户详情……", s);

        UserLoginInfoVO loginAdmin = userMapper.getLoginInfoByUsername(s);
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

        UsersDetails adminDetails = new UsersDetails(
                loginAdmin.getUsername(), loginAdmin.getPassword(),
                loginAdmin.getEnable() == 1, authorities);
        adminDetails.setId(loginAdmin.getId());

//        UserDetails userDetails = User.builder()
//                .username(loginAdmin.getUsername())
//                .password(loginAdmin.getPassword())
//                .accountExpired(false) // 账号是否已过期
//                .accountLocked(false) // 账号是否已锁定
//                .credentialsExpired(false) // 凭证是否已过期
//                .disabled(loginAdmin.getEnable() == 0) // 账号是否已禁用
//                .authorities(loginAdmin.getPermissions().toArray(new String[] {})) // 权限，【注意】必须调用此方法表示此用户具有哪些权限
//                .build();
        log.debug("即将向Spring Security框架返回UserDetails对象：{}", adminDetails);
        return adminDetails;
    }

}
