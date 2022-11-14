package cn.tedu.galaxy.film.controller;

import cn.tedu.galaxy.commons.pojo.film.dto.FilmAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.FilmUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Film;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListByCinemaIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.FilmListItemVO;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.film.service.IFilmService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "电影接口")
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private IFilmService filmService;

    public FilmController() {
        log.info("创建控制器：FilmController");
    }

    // 添加电影
    // http://localhost:9222/films/save
    @ApiOperation("添加电影")
    @ApiOperationSupport(order = 100)
    @PostMapping("/save")
    public JsonResult<Void> save(@Validated @RequestBody FilmAddNewDTO filmAddNewDTO) {
        log.debug("开始处理【添加电影】的请求：{}", filmAddNewDTO);
        filmService.save(filmAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9222/films/9527/delete
    @ApiOperation("删除电影")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "电影id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除电影】的请求：id={}", id);
        filmService.deleteById(id);
        return JsonResult.ok();
    }

    // http://localhost:9222/films/update
    @ApiOperation("修改电影详情")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParam(name = "filmUpdateDTO", value = "更新film")
    @PostMapping("/update")
    public JsonResult<Void> updateById(FilmUpdateDTO filmUpdateDTO) {
        log.debug("开始处理【修改电影详情】的请求：filmUpdateDTO={}", filmUpdateDTO);
        filmService.updateById(filmUpdateDTO);
        return JsonResult.ok();
    }

    // http://localhost:9222/films
    @ApiOperation("查询电影列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult<List<FilmListItemVO>> list() {
        log.debug("开始处理【查询电影列表】的请求……");
        List<FilmListItemVO> list = filmService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("查询电影类型关联列表")
    @ApiOperationSupport(order = 401)
    @GetMapping("/listBy/{type}/{region}/{year}")
    public JsonResult<List<FilmListItemVO>> listBy(@PathVariable String type,
                                                   @PathVariable String region,
                                                   @PathVariable String year) {
        log.debug("开始处理【根据条件查询电影列表】的请求……");
        List<FilmListItemVO> list = filmService.listBy(type, region, year);
        return JsonResult.ok(list);
    }

    @GetMapping("/hot")
    @ApiOperation("获取热榜电影")
    public JsonResult<List<FilmListItemVO>> listHots() {
        List<FilmListItemVO> hots = filmService.findHots();
        return JsonResult.ok(hots);
    }
//    @ApiOperation("查询电影列表")
//    @ApiOperationSupport(order = 400)
//    @GetMapping("")
//    public List<FilmListItemVO> list() {
//        log.debug("开始处理【查询电影列表】的请求……");
//        List<FilmListItemVO> list = filmService.list();
//        return list;
//    }

    //    @GetMapping("/name/{name}")
//    @ApiOperation("搜索电影")
//    public JsonResult<List<FilmListItemVO>> search(@PathVariable String name) {
//        List<FilmListItemVO> filmLikeName = filmService.findFilmLikeName(name);
//        return JsonResult.ok(filmLikeName);
//    }
    @GetMapping("/{state}")
    @ApiOperation(value = "根据状态查找电影")
    public JsonResult<List<FilmListItemVO>> findFilmByState(@PathVariable int state){
        List<FilmListItemVO> allFilm = filmService.findAllFilm(state);
        return JsonResult.ok(allFilm);
    }

//    @GetMapping("/{id}")
//    @ApiOperation(value = "根据id查找电影")
//    public JsonResult<Film> findFilmById(@PathVariable Long id) {
//        Film filmById = filmService.findFilmById(id);
//        return JsonResult.ok(filmById);
//    }

    // 根据影院id查询电影列表
    @GetMapping("/{cinemaId:[0-9]+}/listByCinemaId")
    @ApiOperation("根据影院id查询电影列表")
    public JsonResult<List<FilmListByCinemaIdVO>> listByCinemaId(@PathVariable Long cinemaId){
        List<FilmListByCinemaIdVO> listByCinemaId = filmService.listByCinemaId(cinemaId);
        return JsonResult.ok(listByCinemaId);
    }
}
