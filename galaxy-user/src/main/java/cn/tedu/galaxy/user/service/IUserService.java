package cn.tedu.galaxy.user.service;

import cn.tedu.galaxy.commons.pojo.film.dto.OrderAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.UserOrderStandardVO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserAddNewDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserNoteLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserUpdateDTO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserListItemVO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserStandardVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IUserService {
    /**
     * 买票
     */
    void buyTicket(OrderAddNewDTO orderAddNewDTO);

    /**
     * 用户登录
     *
     * @param userLoginDTO 封装了用户登录相关数据的对象
     * @return 登录成功后此用户的JWT数据
     */
    String login(UserLoginInfoDTO userLoginDTO);
    /**
     * 添加用户
     *
     * @param userAddNewDTO 添加的用户对象
     */
    void addNew(UserAddNewDTO userAddNewDTO);

    /**
     * 删除用户
     *
     * @param id 被删除的用户的id
     */
    void deleteById(Long id);

    /**
     * 根据相册id，修改用户详情
     *
     * @param id            用户id
     * @param userAddNewDTO 新的用户数据
     */
    void updateById(Long id, UserUpdateDTO userAddNewDTO);

    /**
     * 根据id获取用户的标准信息
     *
     * @param id 用户id
     * @return 返回匹配的用户的标准信息，如果没有匹配的数据，将返回null
     */
    UserStandardVO getStandardById(Long id);

    /**
     * 查询用户列表
     *
     * @return 用户列表
     */
    List<UserListItemVO> list();

    /**
     * 发送短信
     * @param phone
     */
    void note (String phone);

    /**
     * 短信登录
     * @param userNoteLoginInfoDTO
     * @return
     */
    void  noteLogin(UserNoteLoginInfoDTO userNoteLoginInfoDTO);

    JsonPage<UserOrderStandardVO> getStandardByUserId(Long userId ,Integer page ,Integer pageSize);

}
