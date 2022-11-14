package cn.tedu.galaxy.admin.controller;

import cn.tedu.galaxy.admin.service.IAdminService;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminAddNewDTO;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminLoginInfoDTO;
import cn.tedu.galaxy.commons.pojo.admin.dto.AdminUpdateInfoDTO;
import cn.tedu.galaxy.commons.pojo.admin.security.LoginPrincipal;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminListItemVO;
import cn.tedu.galaxy.commons.pojo.admin.vo.AdminStandardVO;
import cn.tedu.galaxy.commons.restful.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;


import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admins")
@Api(tags = "管理员模块")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    @PostMapping("/login")
    @ApiOperation("管理员登录")
    @ApiOperationSupport(order = 50)
    public JsonResult<String> login(AdminLoginInfoDTO adminLoginInfoDTO) {
        String jwt = adminService.login(adminLoginInfoDTO);
        return JsonResult.ok(jwt);
    }

    @PostMapping("/add-new")
    @ApiOperation("添加管理员")
    @ApiOperationSupport(order = 100)
    public JsonResult<Void> addNew(AdminAddNewDTO adminAddNewDTO) {
        log.debug("开始处理【添加管理员】的请求，参数：{}", adminAddNewDTO);
        adminService.addNew(adminAddNewDTO);
        return JsonResult.ok();
    }

    @ApiOperation("根据id删除管理员")
    @ApiOperationSupport(order = 200)
    @ApiImplicitParam(name = "id", value = "管理员id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/delete")
    public JsonResult<Void> deleteById(@PathVariable Long id) {
        log.debug("开始处理【根据id删除管理员】的请求：id={}", id);
        adminService.deleteById(id);
        return JsonResult.ok();
    }

    @ApiOperation("修改管理员密码")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "管理员id", required = true, dataType = "long"),
            @ApiImplicitParam(name = "password", value = "新密码", required = true, dataType = "string"),
    })
    @PostMapping("/{id:[0-9]+}/password/change")
    public JsonResult<Void> updatePasswordById(@PathVariable Long id, String password) {
        log.debug("开始处理【修改管理员密码】的请求，id={}，password={}", id, password);
        adminService.updatePasswordById(id, password);
        return JsonResult.ok();
    }

    @ApiOperation("修改管理员基本资料")
    @ApiOperationSupport(order = 301)
    @ApiImplicitParam(name = "id", value = "管理员id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/info/update")
    public JsonResult<Void> updateInfoById(@PathVariable Long id, AdminUpdateInfoDTO adminUpdateInfoDTO) {
        log.debug("开始处理【修改管理员基本资料】的请求，id={}，新基本资料={}", id,adminUpdateInfoDTO);
        adminService.updateInfoById(id, adminUpdateInfoDTO);
        return JsonResult.ok();
    }

    @ApiOperation("启用管理员账号")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParam(name = "id", value = "管理员id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/enable")
    public JsonResult<Void> setEnable(@PathVariable Long id) {
        log.debug("开始处理【启用管理员账号】的请求：id={}", id);
        adminService.setEnable(id);
        return JsonResult.ok();
    }

    @ApiOperation("禁用管理员账号")
    @ApiOperationSupport(order = 311)
    @ApiImplicitParam(name = "id", value = "管理员id", required = true, dataType = "long")
    @PostMapping("/{id:[0-9]+}/disable")
    public JsonResult<Void> setDisable(@PathVariable Long id) {
        log.debug("开始处理【禁用管理员账号】的请求：id={}", id);
        adminService.setDisable(id);
        return JsonResult.ok();
    }

    @ApiOperation("获取管理员详情")
    @ApiOperationSupport(order = 350)
    @GetMapping("/{id:[0-9]+}/detail")
    public JsonResult<AdminStandardVO> AdminDetail(@PathVariable Long id) {
        log.debug("开始处理【获取管理员详情】的请求……");
        AdminStandardVO queryResult = adminService.getStandardById(id);
        return JsonResult.ok(queryResult);
    }

    @ApiOperation("查询管理员列表")
    @ApiOperationSupport(order = 400)
    @GetMapping("")
    public JsonResult<List<AdminListItemVO>> list() {
        log.debug("开始处理【查询管理员列表】的请求……");
        List<AdminListItemVO> list = adminService.list();
        return JsonResult.ok(list);
    }

    @ApiOperation("查询当前管理员登录信息")
    @ApiOperationSupport(order = 410)
    @GetMapping("/info")
    public JsonResult<LoginPrincipal> loginInfo(
            @ApiIgnore @AuthenticationPrincipal LoginPrincipal loginPrincipal) {
        return JsonResult.ok(loginPrincipal);
    }
}
