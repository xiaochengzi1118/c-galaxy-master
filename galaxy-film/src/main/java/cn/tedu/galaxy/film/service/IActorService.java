package cn.tedu.galaxy.film.service;

import cn.tedu.galaxy.commons.pojo.film.dto.ActorAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.ActorListItemVO;

import java.util.List;

public interface IActorService {
    //新增演员
    void save(ActorAddNewDTO actorAddNewDTO);

    //根据id删除演员
    //void deleteById(Long id);
    //根据id修改演员
    //void updateById(Long id, ActorUpdateDTO actorUpdateDTO);
    //查询演员列表
    List<ActorListItemVO> list();
}
