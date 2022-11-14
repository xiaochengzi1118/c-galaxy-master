package cn.tedu.galaxy.film.controller;

import cn.tedu.galaxy.commons.pojo.film.dto.CommentAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.dto.ScoreAddNewDTO;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.film.service.IScoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Slf4j
@RestController
@Api(tags = "评分接口")
@RequestMapping("/scores")
public class ScoreController {
    @Autowired
    private IScoreService scoreService;

    @ApiOperation("获取当前电影评分")
    @GetMapping("/{id}")
    public JsonResult<Double> getScoreByFilmId(@PathVariable Long id) {
        BigDecimal result = scoreService.getScoreByFilmId(id);
        return JsonResult.ok(result.doubleValue());
    }
}
