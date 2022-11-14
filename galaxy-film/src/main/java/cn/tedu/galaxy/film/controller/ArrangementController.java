package cn.tedu.galaxy.film.controller;

import cn.tedu.galaxy.commons.pojo.film.dto.ArrangementAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ArrangementUpdateDTO;
import cn.tedu.galaxy.commons.pojo.film.entity.Arrangement;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListByFilmIdVO;
import cn.tedu.galaxy.commons.pojo.film.vo.ArrangementListItemVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.film.service.IArrangementService;
import com.github.pagehelper.PageInfo;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@Api(tags = "管理场次模块")
@RequestMapping("/arrangements")
public class ArrangementController {

    @Autowired
    private IArrangementService arrangementService;

    public ArrangementController() {
        log.info("创建控制器：ArrangementController");
    }

    // 添加排片
    // http://localhost:9222/arrangements/save
    @ApiOperation("添加场次")
    @ApiOperationSupport(order = 100)
    @PostMapping("/save")
    public JsonResult<Void> save(@Validated @RequestBody ArrangementAddNewDTO arrangementAddNewDTO) {
        log.debug("开始处理【添加排片】的请求：{}", arrangementAddNewDTO);
        arrangementService.save(arrangementAddNewDTO);
        return JsonResult.ok();
    }

    // 删除排片
    // http://localhost:9222/arrangements/{id}/delete
    @ApiOperation("删除场次")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "场次id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除场次】的请求：id={}", id);
        arrangementService.deleteById(id);
        return JsonResult.ok();
    }

    // http://localhost:9222/arrangements/{id}/update
    @ApiOperation("修改场次详情")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParam(name = "arrangementUpdateDTO", value = "更新arrangement")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable @RequestBody Long id, @RequestBody ArrangementUpdateDTO arrangementUpdateDTO) {
        log.debug("开始处理【修改电影详情】的请求：arrangementUpdateDTO={}", arrangementUpdateDTO);
        arrangementService.updateById(id, arrangementUpdateDTO);
        return JsonResult.ok();
    }

    // http://localhost:9222/arrangements/{id}
    @ApiOperation("根据id查询场次")
    @ApiOperationSupport(order = 301)
    @ApiImplicitParam(name = "id", value = "场次id")
    @GetMapping("/{id:[0-9]+}")
    public JsonResult<Arrangement> getStandardById(@PathVariable Long id) {
        Arrangement arrangementById = arrangementService.getArrangementById(id);
        return JsonResult.ok(arrangementById);
    }

    // http://localhost:9222/arrangements
    @ApiOperation("查询场次列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult<List<ArrangementListItemVO>> list() {
        log.debug("开始处理【查询场次列表】的请求……");
        List<ArrangementListItemVO> list = arrangementService.list();
        return JsonResult.ok(list);
    }

    // http://localhost:9222/arrangements/{filmId}/listByFilmId
    @ApiOperation("根据电影id查询场次")
    @GetMapping("/{filmId:[0-9]+}/listByFilmId")
    public JsonResult<List<ArrangementListByFilmIdVO>> listByFilmId(@PathVariable Long filmId){
        log.debug("开始处理【根据电影id和日期查询场次列表】的请求……");
        List<ArrangementListByFilmIdVO> listByFilmId = arrangementService.listByFilmId(filmId);
        return JsonResult.ok(listByFilmId);
    }

    @GetMapping("/page/{page:[0-9]+}/{pageSize:[0-9]+}")
    @ApiOperation("分页查询所有场次")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "页码",name="page",example = "1"),
            @ApiImplicitParam(value = "每页条数",name="pageSize",example = "5")
    })
    public JsonResult<JsonPage<Arrangement>> pageOrders(@PathVariable Integer page,@PathVariable Integer pageSize){
        JsonPage<Arrangement> JsonPage=arrangementService.getAllArrangementByPage(page,pageSize);
        return JsonResult.ok(JsonPage);
    }


}
