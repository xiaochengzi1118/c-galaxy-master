package cn.tedu.galaxy.user.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.OrderAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.UserOrderStandardVO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserAddNewDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserNoteLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserUpdateDTO;
import cn.tedu.galaxy.commons.pojo.user.entity.User;
import cn.tedu.galaxy.commons.pojo.user.entity.UserRole;
import cn.tedu.galaxy.commons.pojo.user.vo.UserListItemVO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserLoginInfoVO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserStandardVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import cn.tedu.galaxy.order.service.IOrderService;
import cn.tedu.galaxy.user.mapper.UserMapper;
import cn.tedu.galaxy.user.mapper.UserRoleMapper;
import cn.tedu.galaxy.user.redis.IUserRedisRepository;
import cn.tedu.galaxy.user.redis.impl.UserRedisRepositoryImpl;
import cn.tedu.galaxy.user.security.UsersDetails;
import cn.tedu.galaxy.user.service.IUserService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.api.common.ResponseCode;
import com.zhenzi.sms.ZhenziSmsClient;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;

import static cn.tedu.galaxy.user.redis.IUserRedisRepository.KEY_CATEGORY_ITEM_PREFIX;


@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserRedisRepository iUserRedisRepository;

    @DubboReference
    private IOrderService dubboOrderService;


    @Value("${galaxy.jwt.secret-key}")
    private String secretKey;
    @Value("${galaxy.jwt.duration-in-minute}")
    private Long durationInMinute;

    public UserServiceImpl(){
        log.info("创建业务对象：UserServiceImpl");
    }


    // Global全局,Transactional事务
    // 一旦一个方法上标记的@GlobalTransactional注解
    // 就相当于设置了分布式事务的起点,当前模块就是分布式事务模型中的TM
    // 最终效果就是当前方法开始,之后所有的远程调用操作数据库的结果就会实现事务的特征
    // 也就是说要么都执行,要么都不执行
    //@GlobalTransactional
    @Override
    public void buyTicket(OrderAddNewDTO orderAddNewDTO) {
        // 从前端获取的orderAddNewDTO信息
        // dubbo调用orderService增加订单
        log.info("即将新增的订单信息:{}",orderAddNewDTO);
        dubboOrderService.addOrder(orderAddNewDTO);
    }

    @Override
    public String login(UserLoginInfoDTO userLoginInfoDTO) {
        log.debug("开始处理【登录认证】的业务，参数：{}", userLoginInfoDTO);

        // 调用AuthenticationManager的authenticate()方法执行认证
        // 在authenticate()方法的执行过程中
        // Spring Security会自动调用UserDetailsService对象的loadUserByUsername()获取用户详情
        // 并根据loadUserByUsername()返回的用户详情自动验证是否启用、判断密码是否正确等
        Authentication authentication
                = new UsernamePasswordAuthenticationToken(
                userLoginInfoDTO.getUsername(),
                userLoginInfoDTO.getPassword());
        Authentication authenticateResult
                = authenticationManager.authenticate(authentication);
        log.debug("Spring Security已经完成认证，且认证通过，返回的结果：{}", authenticateResult);
        log.debug("返回认证信息中的当事人（Principal）类型：{}", authenticateResult.getPrincipal().getClass().getName());
        log.debug("返回认证信息中的当事人（Principal）数据：{}", authenticateResult.getPrincipal());

        // 从认证返回结果中取出当事人信息
        UsersDetails principal = (UsersDetails) authenticateResult.getPrincipal();
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
    public void addNew(UserAddNewDTO userAddNewDTO) {
        log.debug("开始处理【添加用户】的业务，参数：{}", userAddNewDTO);
        String username = userAddNewDTO.getUsername();
        int countByUsername = userMapper.countByUsername(username);
        log.debug("尝试添加的用户名称是：{}，在数据库中此名称的用户数量为：{}", username, countByUsername);
        // 判断统计结果是否大于0
        if (countByUsername > 0) {
            // 是：相册名称已经存在，抛出RuntimeException异常
            String message = "添加用户失败！用户名称【" + username + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        String phone = userAddNewDTO.getPhone();
        int countByPhone = userMapper.countByPhone(phone);
        log.debug("尝试添加的手机号码名称是：{}，在数据库中此名称的手机号码数量为：{}", phone, countByPhone);
        // 判断统计结果是否大于0
        if (countByPhone > 0) {
            // 是：相册名称已经存在，抛出RuntimeException异常
            String message = "添加手机号码败！手机号码【" + phone + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        String email = userAddNewDTO.getEmail();
        int countByEmail = userMapper.countByEmail(email);
        log.debug("尝试添加的邮箱名称是：{}，在数据库中此名称的邮箱数量为：{}", email, countByEmail);
        if (countByEmail > 0) {
            // 是：相册名称已经存在，抛出RuntimeException异常
            String message = "添加邮箱失败！邮箱【" + email + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        User user =new User();
        BeanUtils.copyProperties(userAddNewDTO,user);
        user.setEnable(1);

        // 取出Admin对象中的密码，将其加密，再存入到Admin对象中
        String rawPassword = user.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        log.debug("即将向数据库中插入数据：{}", user);
        int rows = userMapper.insert(user);
        UserRole userRole =new UserRole();
        userRole.setRoleId(2L);
        userRole.setUserId(user.getId());
        userRoleMapper.insertBatch(userRole);
        if (rows != 1) {
            String message = "添加用户失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    @Override
    public void deleteById(Long id) {
        log.debug("开始处理【删除用户】的业务，参数：{}", id);
        UserStandardVO standardById = userMapper.getStandardById(id);
        if (standardById == null) {
            // 是：此id对应的数据不存在，则抛出异常(ERR_NOT_FOUND)
            String message = "删除用户名失败，尝试删除的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        // 调用Mapper对象的deleteById()执行删除，并获取返回值
        int rows = userMapper.deleteById(id);
        // 判断以上返回值是否不为1
        if (rows != 1) {
            // 是：抛出异常(ERR_DELETE)
            String message = "删除用户名失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
         rows = userRoleMapper.deleteByUser(id);
        if (rows < 1) {
            // 抛出ServiceException，业务状态码：DELETE对应的常量
            String message = "删除用户失败！服务器忙，请稍后再次尝试！[错误代码：2]";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_DELETE, message);
        }
    }

    @Override
    public void updateById(Long id, UserUpdateDTO userUpdateDTO) {
        log.debug("开始处理【修改用户名详情】的业务,参数：{}新基本资料={}", id,userUpdateDTO);
        UserStandardVO queryResult = userMapper.getStandardById(id);
        if (queryResult == null) {
            // 是：此id对应的数据不存在，则抛出异常(ERR_NOT_FOUND)
            String message = "修改用户名详情失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        User user =new User();
        BeanUtils.copyProperties(userUpdateDTO,user);
        user.setId(id);

        log.debug("即将修改数据：{}", user);
        int rows = userMapper.update(user);
        if (rows != 1) {
            String message = "修改用户名详情失败，服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_UPDATE, message);
        }
    }

    @Override
    public UserStandardVO getStandardById(Long id) {
        log.debug("开始处理【根据id查询用户名详情】的业务");
        UserStandardVO username = userMapper.getStandardById(id);
        if (username == null) {
            // 是：此id对应的数据不存在，则抛出异常(ERR_NOT_FOUND)
            String message = "获取用户名详情失败，尝试访问的数据不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }
        return username;
    }

    @Override
    public List<UserListItemVO> list() {
        log.debug("开始处理【查询用户名列表】的业务");
        return userMapper.list();
    }

    @Override
    public void note(String phone) {
        log.debug("/////////{}",phone);
        log.debug("开始准备发送手机验证码.....");

        //查询一定时间内该手机号码发送短信的次数
        Serializable count = iUserRedisRepository.selectCount(phone);
        log.debug("查询到的短信发送次数:{}",count);
        //判断是否超过限制次数
        if ("5".equals(count)) {
            //是，报告异常（ERR_PHONE_VERIFICATION_FREQUENTLY）
            String message = "发送短信次数过于频繁,请稍后重试";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND, message);
        }

        //臻子云服务器路径
        String apiUrl = "https://sms_developer.zhenzikj.com";
        //臻子云系统上获取用户id
        String appId = "112582";
        //用户识别码
        String appSecret = "b60aaa13-6f40-4785-aac4-a2756c4ce6bc";

        //生成随机验证码
        String code =String.valueOf((int)((Math.random()*9+1)*100000));
        log.debug("手机验证码为:{}",code);

        ZhenziSmsClient client=new ZhenziSmsClient(apiUrl,appId,appSecret);

        Map<String,Object> params = new HashMap<>();
        params.put("number",phone);//存入手机号
        params.put("templateId",10729);//存入短信模板
        String[] templateParams =new String[2];
        templateParams[0]=code;//存入随机验证码
        templateParams[1]="1分钟";//设置1分钟
        params.put("templateParams",templateParams);
        String send = null;
        try {
            send = client.send(params);
        }catch (Exception e){
            e.printStackTrace();
        }

        //查询短信平台返回是否发送成功的code
        JSONObject jsonObject = JSONObject.parseObject(send);
        int codeBack = jsonObject.getIntValue("code");
        log.debug("短信平台返回的codeBack={}",codeBack);

        //判断返回的code是否为0
        if (codeBack != 0){
            //是，报告异常（ERR_PHONE_VERIFICATION_FREQUENTLY）
            String message = "发送短信失败,手机号码不正确，请重新输入";
            log.debug(message);
            throw new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }

        //保存验证码
        iUserRedisRepository.save(phone,code);
        //增加短信发送次数
        iUserRedisRepository.addCount(phone);
    }

    @Override
    public void noteLogin(UserNoteLoginInfoDTO userNoteLoginInfoDTO ) {
        log.debug("开始处理【短信登录】的业务，参数：{}", userNoteLoginInfoDTO);
        //取出对象和数据库对比
        String phone = userNoteLoginInfoDTO.getPhone();

        String code = userNoteLoginInfoDTO.getCode();
        //从reids 获取 保存的 手机号(key) 验证码(value
        String verificationCodeByPhone = iUserRedisRepository.getVerificationCodeByPhone(phone);
        log.debug("{}",verificationCodeByPhone);
        //) 验证 登录
        if (!verificationCodeByPhone.equals(code)){
            String message = "验证码错误";
            throw  new ServiceException(ServiceCode.ERR_NOT_FOUND,message);
        }
//        UserStandardVO byPhone = userMapper.getStandardByPhone(phone);
//        Long id = byPhone.getId();
//        String username = byPhone.getUsername();
//        UserLoginInfoVO loginInfoByUsername = userMapper.getLoginInfoByUsername(username);
    }

    @Override
    public JsonPage<UserOrderStandardVO> getStandardByUserId(Long userId ,Integer page ,Integer pageSize) {
        JsonPage<UserOrderStandardVO> standardByUserId = dubboOrderService.getStandardByUserId(userId, page, pageSize);
        if (standardByUserId==null){
            String message = "查看订单失败,订单不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        return standardByUserId;
    }


}
