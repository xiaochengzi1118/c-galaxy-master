package cn.tedu.galaxy.commons.exception.handler;

import cn.tedu.galaxy.commons.exception.ServiceCode;
import cn.tedu.galaxy.commons.exception.ServiceException;
import cn.tedu.galaxy.commons.restful.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;


/**
 * 全局异常处理器
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    public GlobalExceptionHandler() {
        log.debug("创建全局异常处理器：GlobalExceptionHandler");
    }

    @ExceptionHandler
    public JsonResult<Void> handleServiceException(ServiceException e) {
        log.debug("处理ServiceException，serviceCode={}，message={}", e.getServiceCode(), e.getMessage());
        return JsonResult.fail(e);
    }

    @ExceptionHandler
    public JsonResult<Void> handleBindException(BindException e) {
        log.debug("处理BindException");

        Integer serviceCode = ServiceCode.ERR_BAD_REQUEST.getValue();

        StringBuilder messageBuilder = new StringBuilder();
        List<FieldError> fieldErrors = e.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            messageBuilder.append(fieldError.getDefaultMessage());
        }

        String message = messageBuilder.toString();
        return JsonResult.fail(serviceCode, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleConstraintViolationException(ConstraintViolationException e) {
        log.debug("处理ConstraintViolationException");

        Integer serviceCode = ServiceCode.ERR_BAD_REQUEST.getValue();

        StringBuilder messageBuilder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            messageBuilder.append(constraintViolation.getMessage());
        }

        String message = messageBuilder.toString();
        return JsonResult.fail(serviceCode, message);
    }

    @ExceptionHandler({
            InternalAuthenticationServiceException.class,
            BadCredentialsException.class
    })
    public JsonResult<Void> handleAuthenticationException(AuthenticationException e) {
        log.debug("处理AuthenticationException");
        log.debug("异常类型：{}", e.getClass().getName());
        log.debug("异常信息：{}", e.getMessage());
        Integer serviceCode = ServiceCode.ERR_UNAUTHORIZED.getValue();
        String message = "登录失败，用户名或密码错误！";
        return JsonResult.fail(serviceCode, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleDisabledException(DisabledException e) {
        log.debug("处理DisabledException");
        Integer serviceCode = ServiceCode.ERR_UNAUTHORIZED_DISABLED.getValue();
        String message = "登录失败，此账号已经禁用！";
        return JsonResult.fail(serviceCode, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleAccessDeniedException(AccessDeniedException e) {
        log.debug("处理AccessDeniedException");
        Integer serviceCode = ServiceCode.ERR_FORBIDDEN.getValue();
        String message = "请求失败，当前账号无此操作权限！";
        return JsonResult.fail(serviceCode, message);
    }

    @ExceptionHandler
    public JsonResult<Void> handleThrowable(Throwable e) {
        log.debug("处理Throwable");
        e.printStackTrace();

        Integer serviceCode = 99999;
        String message = "程序运行过程中出现未知错误，请联系系统管理员！";
        return JsonResult.fail(serviceCode, message);
    }

}