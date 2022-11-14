package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.Region;
import cn.tedu.galaxy.commons.pojo.film.vo.RegionListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.RegionStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionMapper {
    /**
     * 插入电影地区数据
     *
     * @param region 电影地区数据
     * @return 受影响的行数
     */
    int insert(Region region);

    /**
     * 根据id删除电影地区
     *
     * @param id 被删除的电影地区的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改电影地区数据详情
     *
     * @param region 封装了id与新数据的电影地区对象
     * @return 受影响的行数
     */
    int updateById(Region region);

    /**
     * 统计当前表中电影地区数据的数量
     *
     * @return 当前表中电影地区数据的数量
     */
    int count();

    /**
     * 根据电影地区名称统计当前表中电影地区数据的数量
     *
     * @param name 电影地区名称
     * @return 当前表中匹配名称的电影地区数据的数量
     */
    int countByName(String name);

    /**
     * 根据id获取电影地区的标准信息
     *
     * @param id 电影地区id
     * @return 返回匹配的相册的标准信息，如果没有匹配的数据，将返回null
     */
    RegionStandardVO getStandardById(Long id);

    /**
     * 查询电影地区列表
     *
     * @return 电影地区列表
     */
    List<RegionListItemVO> list();


}
