package cn.tedu.galaxy.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// Mybatis框架要求扫描指定的包才能使框架生效
@MapperScan("cn.tedu.galaxy.order.mapper")
public class MybatisConfiguration {
}
