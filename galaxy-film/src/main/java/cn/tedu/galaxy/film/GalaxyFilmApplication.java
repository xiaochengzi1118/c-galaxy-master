package cn.tedu.galaxy.film;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableDubbo
@SpringBootApplication
@MapperScan("cn.tedu.galaxy.film.mapper")
public class GalaxyFilmApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalaxyFilmApplication.class, args);
    }

}
