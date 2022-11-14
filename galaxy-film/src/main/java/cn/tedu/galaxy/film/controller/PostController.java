package cn.tedu.galaxy.film.controller;

import cn.tedu.galaxy.commons.pojo.film.vo.PosterListItemVO;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.film.service.IPostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "电影海报接口")
@RequestMapping("/posters")
public class PostController {
    @Autowired
    private IPostService postService;

    @ApiOperation("获取所有海报")
    @GetMapping("")
    public JsonResult<List<PosterListItemVO>> list() {
        List<PosterListItemVO> allPoster = postService.findAllPoster();
        return JsonResult.ok(allPoster);
    }
}
