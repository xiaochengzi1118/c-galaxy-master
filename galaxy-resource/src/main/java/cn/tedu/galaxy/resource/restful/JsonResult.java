package cn.tedu.galaxy.resource.restful;

import cn.tedu.galaxy.resource.exception.ServiceCode;
import cn.tedu.galaxy.resource.exception.ServiceException;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一的响应结果类型
 *
 * @author java@tedu.cn
 * @version 0.0.1
 */
@Data
public class JsonResult<T> implements Serializable {

    /**
     * 业务状态码
     */
    private Integer state;
    /**
     * 错误时的提示消息
     */
    private String message;
    /**
     * 成功时响应的数据
     */
    private T data;

    public JsonResult() {
    }

    private JsonResult(Integer state, String message, T data) {
        this.state = state;
        this.message = message;
        this.data = data;
    }

    public static JsonResult<Void> ok() {
        return ok(null);
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult(ServiceCode.OK.getValue(), null, data);
    }

    public static JsonResult<Void> fail(ServiceException e) {
        return fail(e.getServiceCode().getValue(), e.getMessage());
    }

    public static JsonResult<Void> fail(Integer state, String message) {
        return new JsonResult(state, message, null);
    }

}