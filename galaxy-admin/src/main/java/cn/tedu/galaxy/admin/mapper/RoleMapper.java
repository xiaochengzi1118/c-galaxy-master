package cn.tedu.galaxy.admin.mapper;


import cn.tedu.galaxy.commons.pojo.admin.vo.RoleListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 处理角色数据的Mapper接口
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Repository
public interface RoleMapper {

    /**
     * 根据id统计角色数据的数量，通常用于检查id值的有效性
     *
     * @param id id
     * @return 统计结果
     */
    int countById(Long id);

    /**
     * 查询角色列表
     *
     * @return 角色列表
     */
    List<RoleListItemVO> list();

}
