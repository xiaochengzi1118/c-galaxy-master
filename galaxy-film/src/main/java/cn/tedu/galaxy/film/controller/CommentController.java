package cn.tedu.galaxy.film.controller;

import cn.tedu.galaxy.commons.pojo.film.dto.CommentAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.CommentListItemVO;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.film.service.ICommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "评论接口")
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @ApiOperation("添加电影评论")
    @PostMapping("/save")
    public JsonResult addNew(CommentAddNewDTO commentAddNewDTO) {
        commentService.save(commentAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("获取当前电影评论")
    @GetMapping("/film/{id}")
    public JsonResult<List<CommentListItemVO>> getCommentByFilmId(@PathVariable Long id) {
        List<CommentListItemVO> result = commentService.listByFid(id);
        return JsonResult.ok(result);
    }
}
