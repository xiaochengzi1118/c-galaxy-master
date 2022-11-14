package cn.tedu.galaxy.user.mapper;

import cn.tedu.galaxy.commons.pojo.user.entity.User;
import cn.tedu.galaxy.commons.pojo.user.vo.UserListItemVO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserLoginInfoVO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理用户数据数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface UserMapper {
    /**
     * 插入用户数据
     *
     * @param user 用户数据
     * @return 受影响的行数
     */
    int insert(User user);

    /**
     * 根据id删除用户
     *
     * @param id 用户id
     * @return 受影响的行数
     */

    int deleteById(Long id);
    /**
     * 根据多个id批量删除用户
     *
     * @param ids 期望删除的多个用户数据的id
     * @return 受影响的行数
     */
    int deleteByIds(Long[] ids);

    /**
     * 更新管理员数据
     *
     * @param user 包含了id和新数据的用户数据对象
     * @return 受影响的行数
     */
    int update(User user );

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 匹配用户名的数据
     */
    int countByUsername(String username);



    /**
     * 根据手机号码统计用户的数量
     *
     * @param phone 手机号码
     * @return 匹配手机号码的用户的数据
     */
    int countByPhone(String phone);

    /**
     * 根据电子邮箱统计用户的数量
     *
     * @param email 电子邮箱
     * @return 匹配电子邮箱的用户的数据
     */
    int countByEmail(String email);

    /**
     * 根据id获取用户详情
     *
     * @param id 用户id
     * @return 匹配的用户详情，如果没有匹配的数据，则返回null
     */
    UserStandardVO getStandardById(Long id);

    UserStandardVO getStandardByPhone(String phone);

//    /**
//     * 根据用户名获取用户的登录信息
//     *
//     * @param username 用户名
//     * @return 用户的登录信息，通常包含密码、权限等
//     */
//    UserListItemVO getLoginInfoByUsername(String username);

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<UserListItemVO> list();


    UserLoginInfoVO getLoginInfoByUsername(String usernanme);

    /**
     * 根据id找到对应的用户
     * @param id
     * @return
     */
    User findUserById(long id);

    User phoneUser(Long phone);
}
