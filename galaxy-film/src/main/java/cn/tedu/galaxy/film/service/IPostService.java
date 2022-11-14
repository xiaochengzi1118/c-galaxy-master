package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.vo.PosterListItemVO;

import java.util.List;

public interface IPostService {

    List<PosterListItemVO> findAllPoster();
}
