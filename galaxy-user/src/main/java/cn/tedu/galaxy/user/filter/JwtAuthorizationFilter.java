package cn.tedu.galaxy.user.filter;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.user.security.LoginPrincipal;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class JwtAuthorizationFilter  extends OncePerRequestFilter {
    @Value("${galaxy.jwt.secret-key}")
    private String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        log.debug("处理JWT的过滤器开始执行……");

        // 清除SecurityContext中原有的认证信息
        // 避免曾经成功访问过，后续不携带JWT也能被视为“已认证”
        SecurityContextHolder.clearContext();

        // 尝试从请求头中获取JWT数据
        String jwt = request.getHeader("Authorization");
        log.debug("尝试从请求头中获取JWT数据：{}", jwt);

        // 判断客户端是否携带了有效的JWT数据，如果没有，直接放行
        if (!StringUtils.hasText(jwt) || jwt.length() < 113) {
            log.debug("获取到的JWT被视为【无效】，过滤器执行【放行】");
            filterChain.doFilter(request, response);
            return;
        }

        // 程序执行到此处，表示客户端携带了有效的JWT，则尝试解析
        log.debug("获取到的JWT被视为【有效】，则尝试解析……");
        Claims claims = null;

        response.setContentType("application/json; charset=utf-8");

        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.debug("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            Integer serviceCode = ServiceCode.ERR_JWT_EXPIRED.getValue();
            String message = "登录信息已过期，请重新登录！";
            JsonResult<Void> jsonResult = JsonResult.fail(serviceCode, message);
            String jsonString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonString);
            writer.close();
            return;
        } catch (SignatureException e) {
            log.debug("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            Integer serviceCode = ServiceCode.ERR_JWT_PARSE.getValue();
            String message = "无法获取到有效的登录信息，请重新登录！";
            JsonResult<Void> jsonResult = JsonResult.fail(serviceCode, message);
            String jsonString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonString);
            writer.close();
            return;
        } catch (MalformedJwtException e) {
            log.debug("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            Integer serviceCode = ServiceCode.ERR_JWT_PARSE.getValue();
            String message = "无法获取到有效的登录信息，请重新登录！";
            JsonResult<Void> jsonResult = JsonResult.fail(serviceCode, message);
            String jsonString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonString);
            writer.close();
            return;
        } catch (Throwable e) {
            log.debug("解析JWT失败：{}：{}", e.getClass().getName(), e.getMessage());
            Integer serviceCode = ServiceCode.ERR_JWT_PARSE.getValue();
            String message = "无法获取到有效的登录信息，请重新登录！";
            JsonResult<Void> jsonResult = JsonResult.fail(serviceCode, message);
            String jsonString = JSON.toJSONString(jsonResult);
            PrintWriter writer = response.getWriter();
            writer.println(jsonString);
            writer.close();
            e.printStackTrace();
            return;
        }

        // 从JWT中获取数据
        Long id = claims.get("id", Long.class);
        log.debug("从JWT中解析得到【id】的值：{}", id);
        String username = claims.get("username", String.class);
        log.debug("从JWT中解析得到【username】的值：{}", username);
        String authorityListString = claims.get("authorities", String.class);
        log.debug("从JWT中解析得到【authorities】的值：{}", authorityListString);

        // 准备权限，将封装到认证信息中
        List<SimpleGrantedAuthority> authorityList
                = JSON.parseArray(authorityListString, SimpleGrantedAuthority.class);

        // 创建自定义的当事人类型的对象
        LoginPrincipal loginPrincipal = new LoginPrincipal();
        loginPrincipal.setId(id);
        loginPrincipal.setUsername(username);

        // 准备存入到SecurityContext的认证信息
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                loginPrincipal, null, authorityList);

        // 将认证信息存入到SecurityContext中
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        log.debug("过滤器执行【放行】");
        filterChain.doFilter(request, response);
    }

}

