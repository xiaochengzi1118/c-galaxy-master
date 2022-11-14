package cn.tedu.galaxy.order.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * Id工具类
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
public final class IdUtils {

    private IdUtils() {
    }

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssS");

    private static Random random = new Random();

    // 临时策略：使用“年月日时分秒毫秒”加2位随机数作为id
    public static Long getId() {
        LocalDateTime now = LocalDateTime.now();
        String dateTimeString = dateTimeFormatter.format(now);
        //int randomNumber = random.nextInt(89) + 10;
        Long id = Long.valueOf(dateTimeString);
        return id;
    }

}