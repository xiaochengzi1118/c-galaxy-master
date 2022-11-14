package cn.tedu.galaxy.film.mapper;

import cn.tedu.galaxy.commons.pojo.film.vo.PosterListItemVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PosterMapper {
    /**
     * 获取海报列表
     *
     * @return
     */
    List<PosterListItemVO> list();
}
