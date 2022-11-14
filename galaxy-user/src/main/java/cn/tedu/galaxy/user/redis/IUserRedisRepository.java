package cn.tedu.galaxy.user.redis;

import java.io.Serializable;

public interface IUserRedisRepository {

    String KEY_CATEGORY_ITEM_PREFIX = "user:item";
    String KEY_CATEGORY_ITEM_PREFIX_PHONE = "phone:item";

    // 将手机验证码存入到Redis中
    void save(String phone, String verificationCode);

    // 根据手机获取验证码信息
    String getVerificationCodeByPhone(String phone);

    //查询手机获取验证码次数是否超过限制次数
    Serializable selectCount(String phone);

    //增加手机获取验证码次数
    void addCount(String phone);

}
