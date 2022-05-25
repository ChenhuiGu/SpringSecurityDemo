package cn.guchh.utils;

import cn.guchh.exception.ErrorCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenhuigu
 */
@Data
@NoArgsConstructor
@ApiModel("全局统一返回结果")
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean success = false;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<>();

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(20000);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setCode(ErrorCode.UNKNOWN_REASON.getErrorCode());
        r.setMessage(ErrorCode.UNKNOWN_REASON.getErrorMsg());
        return r;
    }

    public static R setResult(ErrorCode errorCode) {
        R r = new R();
        r.setCode(errorCode.getErrorCode());
        r.setMessage(errorCode.getErrorMsg());
        return r;
    }


    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}
