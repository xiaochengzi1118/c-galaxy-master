package cn.tedu.galaxy.user.config;

import cn.tedu.galaxy.user.filter.JwtAuthorizationFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthorizationFilter jwtAuthorizationFilter;

    public SecurityConfiguration() {
        log.info("加载配置类：SecurityConfiguration");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        // 调用父类的方法得到AuthenticationManager
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http); // 千万不要调用父类的同名方法

        // 白名单
        String[] urls = {
                "/doc.html",
                "/favicon.ico",
                "/**/*.js",
                "/**/*.css",
                "/swagger-resources",
                "/v2/api-docs",
                "/users",
                "/users/**",
                "/users/add-new",
                "/users/{id}/delete",
                "/users/{id}/update",
                "/admins/login",
                "/users/{id}/delete",
                "/users/{id}",
                "/login",
                "/noteLogin",
                "/note",
        };

        // 配置各请求路径是否需要通过认证
        http.authorizeRequests() // 对请求进行授权
                .antMatchers(urls) // 匹配某些路径
                .permitAll() // 允许此前匹配的路径直接访问，不需要通过认证或授权
                // .antMatchers(HttpMethod.OPTIONS,"/**")
                // .permitAll()
                .anyRequest() // 除了以上配置过的其它任何路径
                .authenticated(); // 需要通过认证

        // 允许跨域访问
        http.cors(); // 激活Spring Security框架内置的一个CorsFilter，允许跨域访问

        // 关于防伪造的跨域攻击，默认只针对POST / PUT / DELETE / PATCH请求
        // 禁用防伪造的跨域攻击，注意：禁用后是存在风险的
        http.csrf().disable();

//         将JWT过滤器添加在Spring Security的UsernamePasswordAuthenticationFilter之前
        http.addFilterBefore(jwtAuthorizationFilter,
                UsernamePasswordAuthenticationFilter.class);

        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 不主动创建Session
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER); // 从不创建Session

        // http.formLogin(); // 允许通过 /login 打开登录页面
    }

}
