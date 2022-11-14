package cn.tedu.galaxy.film.controller;


import cn.tedu.galaxy.commons.pojo.film.vo.ScreenListItemVO;
import cn.tedu.galaxy.film.service.IScreenService;
import cn.tedu.galaxy.film.web.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "影厅管理系统")
@RequestMapping("/screens")
public class ScreenController {
    @Autowired
    private IScreenService screenService;

    @ApiOperation("获取所有影院")
    @GetMapping("")
    public JsonResult<List<ScreenListItemVO>> list() {
        List<ScreenListItemVO> cinema = screenService.list();
        return JsonResult.ok(cinema);
    }

    @ApiOperation("根据影厅名获取剩余座位数")
    @GetMapping("/getRemaining/{name}")
    public JsonResult<Integer> getRemaining(@PathVariable String name) {
        int ro = screenService.getRo(name);
        int co = screenService.getCo(name);
        return JsonResult.ok(ro * co);
    }
}
