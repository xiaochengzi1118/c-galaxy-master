package cn.tedu.galaxy.user.redis.impl;

import cn.tedu.galaxy.user.redis.IUserRedisRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class UserRedisRepositoryImpl implements IUserRedisRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(String phone, String verificationCode) {
        //key
        String key = KEY_CATEGORY_ITEM_PREFIX + phone;
        //保存验证码过期时间
        redisTemplate.opsForValue().set(key, verificationCode, 60, TimeUnit.SECONDS);
    }

    @Override
    public String getVerificationCodeByPhone(String phone) {
        String key = KEY_CATEGORY_ITEM_PREFIX + phone;
        // 把 手机号为key 保存进入redis里
        Serializable result = redisTemplate.opsForValue().get(key);
        if (result == null) {
            return null;
        }

        return (String) result;
    }

    @Override
    public Serializable selectCount(String phone) {
        //根据手机号码查询获取短信次数
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        String key = KEY_CATEGORY_ITEM_PREFIX_PHONE + phone;
        Serializable value = opsForValue.get(key);
        return value;
    }

    @Override
    public void addCount(String phone) {
        //根据手机号码查询获取短信次数
        ValueOperations<String, Serializable> opsForValue = redisTemplate.opsForValue();
        String key = KEY_CATEGORY_ITEM_PREFIX_PHONE + phone;
        Serializable value = opsForValue.get(key);
        log.debug("查询结果为{}",value);

        //判断次数是否为空
        if (value == null) {
            //是，存入次数，并设置过期时间
            int count = 1;
            redisTemplate.opsForValue().set(key, String.valueOf(count), 1, TimeUnit.DAYS);
            Serializable v1 = opsForValue.get(phone);
            log.debug("value={}", v1);
        }else {
            //取出发送短信次数，并转Integer类型
            int parseInt = Integer.parseInt(String.valueOf(value));
            //次数加1
            parseInt += 1;
            //重新存入
            opsForValue.set(key, String.valueOf(parseInt), 1, TimeUnit.DAYS);
            Serializable value2 = opsForValue.get(key);
            log.debug("value={}", value2);
        }
    }
}
