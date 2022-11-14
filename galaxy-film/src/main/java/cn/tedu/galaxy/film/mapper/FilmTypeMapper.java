package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.entity.FilmType;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmTypeListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmTypeStandardVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface FilmTypeMapper {
    /**
     * 插入电影类型数据
     *
     * @param filmType 电影类型数据
     * @return 受影响的行数
     */
    int insert(FilmType filmType);

    /**
     * 根据id删除电影类型
     *
     * @param id 被删除的电影类型的id
     * @return 受影响的行数
     */
    int deleteById(Long id);

    /**
     * 根据id修改电影类型数据详情
     *
     * @param filmType 封装了id与新数据的电影类型对象
     * @return 受影响的行数
     */
    int updateById(FilmType filmType);

    /**
     * 统计当前表中电影类型数据的数量
     *
     * @return 当前表中电影类型数据的数量
     */
    int count();

    /**
     * 根据电影类型名称统计当前表中电影类型数据的数量
     *
     * @param name 电影类型名称
     * @return 当前表中匹配名称的电影类型数据的数量
     */
    int countByName(String name);

    /**
     * 根据电影id获取类型数量
     * @param FilmId
     * @return
     */
//    int countByFilm(Long FilmId);

    /**
     * 根据id获取电影类型的标准信息
     *
     * @param id 电影类型id
     * @return 返回匹配的电影类型的标准信息，如果没有匹配的数据，将返回null
     */
    FilmTypeStandardVO getStandardById(Long id);

    /**
     * 查询电影类型列表
     *
     * @return 电影类型列表(用于标签)
     */
    List<FilmTypeListItemVO> getTags();

    /**
     * 获得所有标签对象的Map
     * @return
     */
    Map<String,FilmType> getTypeTagMap();
}
