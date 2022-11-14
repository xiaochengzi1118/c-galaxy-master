package cn.tedu.galaxy.film.controller;


import cn.tedu.galaxy.commons.pojo.film.vo.CinemaListItemVO;
import cn.tedu.galaxy.film.service.ICinemaService;
import cn.tedu.galaxy.film.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "影院管理系统")
@RequestMapping("/cinemas")
public class CinemaController {
    @Autowired
    private ICinemaService cinemaService;

    @ApiOperation("获取所有影院")
    @GetMapping("")
    public JsonResult<List<CinemaListItemVO>> list() {
        List<CinemaListItemVO> cinema = cinemaService.list();
        return JsonResult.ok(cinema);
    }

    @ApiOperation("根据电影id获取影院列表")
    @GetMapping("/{filmId:[0-9]+}/listByFilmId")
    public JsonResult<List<CinemaListItemVO>> listByFilmId(@PathVariable Long filmId){
        List<CinemaListItemVO> listByFilmId = cinemaService.listByFilmId(filmId);
        return JsonResult.ok(listByFilmId);
    }
}
