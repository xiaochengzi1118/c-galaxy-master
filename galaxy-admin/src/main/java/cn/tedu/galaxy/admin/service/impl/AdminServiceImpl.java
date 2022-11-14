package cn.tedu.galaxy.admin.service.impl;

import cn.tedu.galaxy.admin.mapper.AdminMapper;
import cn.tedu.galaxy.admin.mapper.AdminRoleMapper;
import cn.tedu.galaxy.admin.security.AdminDetails;
import cn.tedu.galaxy.admin.service.IAdminService;
import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminAddNewDTO;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminUpdateInfoDTO;
import cn.tedu.galaxy.commons.pojo.admin.entity.Admin;
import cn.tedu.galaxy.commons.pojo.admin.entity.AdminRole;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminListItemVO;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminStandardVO;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


@Slf4j
@Service
public class AdminServiceImpl implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Value("${galaxy.jwt.secret-key}")
    private String secretKey;
    @Value("${galaxy.jwt.duration-in-minute}")
    private Long durationInMinute;

    @Override
    public String login(AdminLoginInfoDTO adminLoginInfoDTO) {
        log.debug("开始处理【登录认证】的业务，参数：{}", adminLoginInfoDTO);

        // 调用AuthenticationManager的authenticate()方法执行认证
        // 在authenticate()方法的执行过程中
        // Spring Security会自动调用UserDetailsService对象的loadUserByUsername()获取用户详情
        // 并根据loadUserByUsername()返回的用户详情自动验证是否启用、判断密码是否正确等
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                adminLoginInfoDTO.getUsername(),
                adminLoginInfoDTO.getPassword());
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("Spring Security已经完成认证，且认证通过，返回的结果：{}", authenticateResult);
        log.debug("返回认证信息中的当事人（Principal）类型：{}", authenticateResult.getPrincipal().getClass().getName());
        log.debug("返回认证信息中的当事人（Principal）数据：{}", authenticateResult.getPrincipal());

        // 从认证返回结果中取出当事人信息
        AdminDetails principal = (AdminDetails) authenticateResult.getPrincipal();
        Long id = principal.getId();
        log.debug("认证信息中的用户id：{}", id);
        String username = principal.getUsername();
        log.debug("认证信息中的用户名：{}", username);
        Collection<GrantedAuthority> authorities = principal.getAuthorities();
        log.debug("认证信息中的权限：{}", authorities);
        String authorityListString = JSON.toJSONString(authorities);
        log.debug("认证信息中的权限转换为JSON字符串：{}", authorityListString);

        // 生成JWT，并返回
        // 准备Claims值
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", id);
        claims.put("username", username);
        claims.put("authorities", authorityListString);

        // JWT的过期时间
        Date expiration = new Date(System.currentTimeMillis() + durationInMinute * 60 * 1000);
        log.debug("即将生成JWT数据，过期时间：{}", expiration);

        // JWT的组成：Header（头：算法和Token类型）、Payload（载荷）、Signature（签名）
        String jwt = Jwts.builder()
                // Header
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                // Payload
                .setClaims(claims)
                .setExpiration(expiration)
                // Signature
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
        log.debug("已经生成JWT数据：{}", jwt);
        return jwt;
    }

    @Override
    public void addNew(AdminAddNewDTO adminAddNewDTO) {
        log.debug("开始处理【添加管理员】的业务，参数：{}", adminAddNewDTO);
        String username = adminAddNewDTO.getUsername();
        log.debug("检查用户名【{}】是否被占用……", username);
        // 调用Mapper对象的countByUsername()根据用户名统计管理员账号的数量
        int countByUsername = adminMapper.countByUsername(username);
        if (countByUsername > 0) {
            String message = "添加管理员失败，用户名【" + username + "】已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        String phone = adminAddNewDTO.getPhone();
        log.debug("检查手机号码【{}】是否被占用……", phone);
        // 调用Mapper对象的countByPhone()根据手机号码统计管理员账号的数量
        int countByPhone = adminMapper.countByPhone(phone);
        if (countByPhone > 0) {
            String message = "添加管理员失败，手机号码【" + phone + "】已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        String email = adminAddNewDTO.getEmail();
        log.debug("检查电子邮箱【{}】是否被占用……", email);
        // 调用Mapper对象的countByEmail()根据电子邮箱统计管理员账号的数量
        int countByEmail = adminMapper.countByEmail(email);
        if (countByEmail > 0) {
            String message = "添加管理员失败，电子邮箱【" + email + "】已经被占用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        Admin admin = new Admin();
        BeanUtils.copyProperties(adminAddNewDTO, admin);
        admin.setLoginCount(0);
        // 取出Admin对象中的密码，将其加密，再存入到Admin对象中
        String rawPassword = admin.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        admin.setPassword(encodedPassword);
        // 调用Mapper对象的insert()插入管理员数据，并获取返回值
        log.debug("即将插入管理员数据：{}", admin);
        int rows = adminMapper.insert(admin);
        if (rows != 1) {
            String message = "添加管理员失败，服务器忙，请稍后再尝试！[错误代码：1]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }

        log.debug("准备插入管理员与角色的关联关系");
        LocalDateTime now = LocalDateTime.now();
        AdminRole adminRole = new AdminRole();
        adminRole.setUserId(admin.getId());
        adminRole.setRoleId(1L);
        adminRole.setGmtCreate(now);
        adminRole.setGmtModified(now);

        rows = adminRoleMapper.insertBatch(adminRole);
        if (rows != 1) {
            String message = "添加管理员失败，服务器忙，请稍后再尝试！[错误代码：2]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
        log.debug("添加管理员完成！");
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【根据id删除管理员】的业务：id={}", id);

        AdminStandardVO queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "删除管理员失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        int rows = adminMapper.deleteById(id);
        if (rows != 1) {
            String message = "删除管理员失败！服务器忙，请稍后再次尝试！[错误代码：1]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }

        // 删除“管理员与角色”的关联数据
        rows = adminRoleMapper.deleteByAdmin(id);
        if (rows != 1) {
            String message = "删除管理员失败！服务器忙，请稍后再次尝试！[错误代码：2]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public void updatePasswordById(Long id, String password) {
        log.debug("开始处理【修改管理员密码】的业务，id={}，password={}", id, password);
        AdminStandardVO queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "修改管理员密码失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        Admin admin = new Admin();
        admin.setId(id);
        String encodedPassword = passwordEncoder.encode(password);
        admin.setPassword(encodedPassword);
        log.debug("即将修改管理员密码：{}", admin);
        int rows = adminMapper.update(admin);
        if (rows != 1) {
            String message = "修改管理员密码失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void updateInfoById(Long id, AdminUpdateInfoDTO adminUpdateInfoDTO) {
        log.debug("开始处理【修改管理员基本资料】的业务，id={}，新基本资料={}",id, adminUpdateInfoDTO);
        AdminStandardVO queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "修改管理员基本资料失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        Admin admin = new Admin();
        BeanUtils.copyProperties(adminUpdateInfoDTO, admin);
        admin.setId(id);
        log.debug("即将修改管理员基本资料：{}", admin);
        int rows = adminMapper.update(admin);
        if (rows != 1) {
            String message = "修改管理员基本资料失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void setEnable(Long id) {
        log.debug("开始处理【启用管理员账号】的业务：id={}", id);

        AdminStandardVO queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "启用管理员账号失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        if (queryResult.getEnable() == 1) {
            String message = "启用管理员账号失败！当前账号已经启用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        Admin admin = new Admin();
        admin.setId(id);
        admin.setEnable(1);
        int rows = adminMapper.update(admin);
        if (rows != 1) {
            String message = "启用管理员账号失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public void setDisable(Long id) {
        log.debug("开始处理【禁用管理员账号】的业务：id={}", id);

        AdminStandardVO queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "禁用管理员账号失败！尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        if (queryResult.getEnable() == 0) {
            String message = "禁用管理员账号失败！当前账号已经禁用！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        Admin admin = new Admin();
        admin.setId(id);
        admin.setEnable(0);
        int rows = adminMapper.update(admin);
        if (rows != 1) {
            String message = "禁用管理员账号失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public AdminStandardVO getStandardById(Long id) {
        AdminStandardVO queryResult = adminMapper.getStandardById(id);
        if (queryResult == null) {
            String message = "尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        return queryResult;
    }

    @Override
    public List<AdminListItemVO> list() {
        log.debug("开始处理【查询管理员列表】的业务……");
        List<AdminListItemVO> list = adminMapper.list();
        log.debug("即将返回【{}条】管理员数据……", list.size());
        for (Object item : list) {
            log.debug("{}", item);
        }
        return list;
    }
}
