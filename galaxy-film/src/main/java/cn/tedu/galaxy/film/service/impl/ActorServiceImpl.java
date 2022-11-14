package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.ActorAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Actor;
import cn.tedu.galaxy.commons.pojo.film.vo.ActorListItemVO;
import cn.tedu.galaxy.film.mapper.ActorMapper;
import cn.tedu.galaxy.film.service.IActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ActorServiceImpl implements IActorService {
    @Autowired
    private ActorMapper actorMapper;

    @Override
    public void save(ActorAddNewDTO actorAddNewDTO) {
        log.debug("开始处理【新增演员】的业务！");
        String name = actorAddNewDTO.getName();
        int countByName = actorMapper.countByName(name);
        log.debug("尝试添加的演员名称是：{}，在数据库中此名称的演员数量为：{}", name, countByName);
        // 判断统计结果是否大于0
        if (countByName > 0) {
            // 是：演员名称已经存在，抛出RuntimeException异常
            String message = "添加演员失败！演员名称【" + name + "】已存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }

        // 创建actor对象
        Actor actor = new Actor();
        BeanUtils.copyProperties(actorAddNewDTO, actor);
        log.debug("即将向数据库中插入数据：{}", actor);
        int rows = actorMapper.insert(actor);
        // 判断插入数据时受影响的行数是否有误
        if (rows != 1) {
            String message = "添加演员失败！服务器忙，请稍后再次尝试！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_INSERT, message);
        }
    }

    //@Override
    //public void deleteById(Long id) {}

    //@Override
    //public void updateById(Long id, ActorUpdateDTO actorUpdateDTO) {}

    @Override
    public List<ActorListItemVO> list() {
        return actorMapper.list();
    }
}
