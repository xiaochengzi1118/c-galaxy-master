package cn.tedu.galaxy.user.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis的配置类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@Configuration
@MapperScan("cn.tedu.galaxy.user.mapper")
public class MybatisConfiguration {

    public MybatisConfiguration() {

      log.info("加载配置类：MybatisConfiguration");
    }

}
