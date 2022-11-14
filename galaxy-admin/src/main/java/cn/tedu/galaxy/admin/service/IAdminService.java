package cn.tedu.galaxy.admin.service;

import cn.tedu.galaxy.commons.pojo.admin.dto.AdminAddNewDTO;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminUpdateInfoDTO;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminListItemVO;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminStandardVO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface IAdminService {
    /**
     * 管理员登录
     *
     * @param adminLoginInfoDTO 封装了管理员登录相关数据的对象
     * @return 登录成功后此用户的JWT数据
     */
    String login(AdminLoginInfoDTO adminLoginInfoDTO);
    /**
     * 增加管理员
     *
     * @param adminAddNewDTO 新增的管理员对象
     */
    void addNew(AdminAddNewDTO adminAddNewDTO);
    /**
     * 根据管理员id删除管理员
     *
     * @param id 管理员id
     */
    void deleteById(Long id);
    /**
     * 直接修改管理员密码（不验证原密码）
     *
     * @param id       管理员id
     * @param password 新密码
     */
    void updatePasswordById(Long id, String password);
    /**
     * 修改管理员基本资料
     *
     * @param id                 管理员id
     * @param adminUpdateInfoDTO 封装了新基本资料的对象
     */
    void updateInfoById(Long id, AdminUpdateInfoDTO adminUpdateInfoDTO);

    /**
     * 启用管理员账号
     *
     * @param id 管理员id
     */
    void setEnable(Long id);

    /**
     * 禁用管理员账号
     *
     * @param id 管理员id
     */
    void setDisable(Long id);
    /**
     * 获取管理员详情
     * @param id 管理员id
     * @return 管理员详情
     */
    AdminStandardVO getStandardById(Long id);
    /**
     * 查询管理员列表
     *
     * @return 管理员列表
     */
    List<AdminListItemVO> list();
}
