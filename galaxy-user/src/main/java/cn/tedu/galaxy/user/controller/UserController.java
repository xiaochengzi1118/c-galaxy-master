package cn.tedu.galaxy.user.controller;

import cn.tedu.galaxy.commons.pojo.film.dto.OrderAddNewDTO;
import cn.tedu.galaxy.commons.pojo.film.vo.UserOrderStandardVO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserAddNewDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserNoteLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.user.dto.UserUpdateDTO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserListItemVO;
import cn.tedu.galaxy.commons.pojo.user.vo.UserStandardVO;
import cn.tedu.galaxy.commons.restful.JsonPage;
import cn.tedu.galaxy.commons.restful.JsonResult;
import cn.tedu.galaxy.user.security.LoginPrincipal;
import cn.tedu.galaxy.user.service.IUserService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@Api(tags = "03,用户管理模块")
public class UserController {

    @Autowired
    private IUserService userService;

    public UserController() {
        log.info("创建控制器：UserController");
    }

    @ApiOperation("买票")
    @ApiImplicitParam()
    @PostMapping("/buyTicket")
    public JsonResult<String> buyTicket(@RequestBody OrderAddNewDTO orderAddNewDTO){
        log.debug("开始处理【买票】的请求，参数：{}", orderAddNewDTO);
        userService.buyTicket(orderAddNewDTO);
        return JsonResult.ok("新增订单成功!");
    }

    @ApiOperation("用户登录")
    @ApiOperationSupport(order = 100)
    @PostMapping("/login")
    public JsonResult<String> login(UserLoginInfoDTO userLoginInfoDTO) {
        log.debug("开始处理【登录认证】的请求，参数：{}", userLoginInfoDTO);
        String jwt = userService.login(userLoginInfoDTO);
        log.debug("即将向客户端响应JWT数据：{}", jwt);
        return JsonResult.ok(jwt);
    }

    // http://localhost:9333/Users/add-new
    @ApiOperation("添加用户")
    @ApiOperationSupport(order = 200)
    @PostMapping("/add-new")
    public JsonResult<Void> addNew(@Validated UserAddNewDTO userAddNewDTO) {
        log.debug("开始处理【添加品牌】的请求：{}", userAddNewDTO);
        userService.addNew(userAddNewDTO);
        return JsonResult.ok();
    }

    // http://localhost:9333/users/3
    @ApiOperation("根据id查询用户名详情")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long")
    @GetMapping("/{id:[0-9]+}/detail")
    public JsonResult<UserStandardVO> getStandardById(@PathVariable Long id) {
        log.debug("开始处理【根据id查询用户详情】的请求：id={}", id);
        UserStandardVO user = userService.getStandardById(id);
        return JsonResult.ok(user);
    }
    // http://localhost:9333/users
    @ApiOperation("查询用户列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult<List<UserListItemVO>> list() {
        log.debug("开始处理【查询用户列表】的请求……");
        List<UserListItemVO> list = userService.list();
        return JsonResult.ok(list);
    }
    // http://localhost:9333/users/9527/delete
    @ApiOperation("删除用户")
    @ApiOperationSupport(order = 500)
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> delete(@PathVariable Long id) {
        log.debug("开始处理【删除用户】的请求：id={}", id);
        userService.deleteById(id);
        return JsonResult.ok();
    }
    // http://localhost:9333/users/3/update
    @ApiOperation("修改用户详情")
    @ApiOperationSupport(order = 600)
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/update")
    public JsonResult<Void> updateById(@PathVariable Long id, UserUpdateDTO userUpdateDTO) {
        log.debug("开始处理【修改用户详情】的请求：id={}, userUpdateDTO={}", id, userUpdateDTO);
        userService.updateById(id, userUpdateDTO);
        return JsonResult.ok();
    }
    @ApiOperation("发送短信")
    @ApiOperationSupport(order = 700)
    @PostMapping("/note")
    public JsonResult note(String phone) {
        log.debug("开始【发送短信】的请求，参数：{}", phone);
        userService.note(phone);
        return JsonResult.ok();
    }


    @ApiOperation("短信用户登录")
    @ApiOperationSupport(order = 800)
    @PostMapping("/noteLogin")
    public JsonResult noteLogin( @Validated UserNoteLoginInfoDTO userNoteLoginInfoDTO) {
        log.debug("开始处理【登录认证】的请求，参数：{}", userNoteLoginInfoDTO);
         userService.noteLogin(userNoteLoginInfoDTO);
        return JsonResult.ok(userNoteLoginInfoDTO);
    }

    @ApiOperation("查询当前用户登录信息")
    @ApiOperationSupport(order = 900)
    @GetMapping("/info")
    public JsonResult<LoginPrincipal> loginInfo(
            @ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
        log.debug("执行查询当前用户登录信息");
        return JsonResult.ok(loginPrincipal);
    }

    @ApiOperation("根据用户id查订单")
    @ApiOperationSupport(order = 910)
    @ApiImplicitParam(name = "UserId", value = "用户id", required = true, dataType = "long")
    @GetMapping("/{UserId:[0-9]+}/{page:[0-9]+}/{pageSize:[0-9]+}/OrderDetail")
    public JsonResult<JsonPage<UserOrderStandardVO>> getStandardByUserId(@PathVariable Long UserId,@PathVariable Integer page,@PathVariable Integer pageSize ) {
        log.debug("开始处理【根据用户id查询订单】的请求：UserId={}", UserId);
        JsonPage<UserOrderStandardVO> jsonPage = userService.getStandardByUserId(UserId, page, pageSize);
        return JsonResult.ok(jsonPage);
    }
}
