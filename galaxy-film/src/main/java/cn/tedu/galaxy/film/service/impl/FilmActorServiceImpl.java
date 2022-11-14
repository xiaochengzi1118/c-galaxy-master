package cn.tedu.galaxy.film.service.impl;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmActorAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmActorUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.FilmActor;
import cn.tedu.galaxy.commons.pojo.film.vo.ActorStandardVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmActorListItemVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmStandardVO;
import cn.tedu.galaxy.film.mapper.ActorMapper;
import cn.tedu.galaxy.film.mapper.FilmActorMapper;
import cn.tedu.galaxy.film.mapper.FilmMapper;
import cn.tedu.galaxy.film.service.IFilmActorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FilmActorServiceImpl implements IFilmActorService {
    @Autowired
    private FilmActorMapper filmActorMapper;
    @Autowired
    private FilmMapper filmMapper;
    @Autowired
    private ActorMapper actorMapper;

    @Override
    public void save(FilmActorAddNewDTO filmActorAddNewDTO) {
        log.info("开始处理【给电影添加演员】的业务！");
        FilmStandardVO film = filmMapper.getStandardById(filmActorAddNewDTO.getFilmId());
        if (film == null) {
            String message = "添加电影演员失败！电影id【" + filmActorAddNewDTO.getFilmId() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        ActorStandardVO actor = actorMapper.getStandardById(filmActorAddNewDTO.getActorId());
        if (actor == null) {
            String message = "添加电影演员失败！演员id【" + filmActorAddNewDTO.getActorId() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        FilmActor filmActor = new FilmActor();
        BeanUtils.copyProperties(filmActorAddNewDTO, filmActor);
        filmActorMapper.insert(filmActor);
    }

    @Override
    public void deleteById(Long id) {
        filmActorMapper.deleteById(id);
        log.info("删除改电影的演员id={}完成！", id);
    }

    @Override
    public void updateById(Long id, FilmActorUpdateDTO filmActorUpdateDTO) {
        log.info("开始处理【更新电影演员信息】的业务！");
        FilmStandardVO film = filmMapper.getStandardById(filmActorUpdateDTO.getFilmId());
        if (film == null) {
            String message = "更新电影演员失败！电影id【" + filmActorUpdateDTO.getFilmId() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        ActorStandardVO actor = actorMapper.getStandardById(filmActorUpdateDTO.getActorId());
        if (actor == null) {
            String message = "更新电影演员失败！演员id【" + filmActorUpdateDTO.getActorId() + "】不存在！";
            log.warn(message);
            throw new ServiceException(ServiceCode.ERR_CONFLICT, message);
        }
        FilmActor filmActor = new FilmActor();
        BeanUtils.copyProperties(filmActorUpdateDTO, filmActor);
        filmActor.setId(filmActorUpdateDTO.getId());
        log.info("完成更新！");
    }

    @Override
    public List<FilmActorListItemVO> list() {
        return filmActorMapper.list();
    }
}
