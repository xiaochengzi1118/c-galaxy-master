package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.pojo.film.vo.PosterListItemVO;
import cn.tedu.galaxy.film.mapper.PosterMapper;
import cn.tedu.galaxy.film.service.IPostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    private PosterMapper posterMapper;

    @Override
    public List<PosterListItemVO> findAllPoster() {
        List<PosterListItemVO> list = posterMapper.list();
        return list;
    }
}
