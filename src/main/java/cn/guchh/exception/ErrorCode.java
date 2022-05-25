package cn.guchh.exception;


import lombok.Getter;

/**
 * @author chenhuigu
 */
@Getter
public enum ErrorCode {
    UNKNOWN_REASON(20001,"未知错误"),
    USER_NAME_ALREADY_EXIST(20002,"用户名已经存在"),
    VERIFY_JWT_FAILED(20003, "token验证失败"),
    MOBILE_IS_NOT_EXIST(20004,"手机号不存在"),
    MOBILE_OR_PASSWD_ERROR(20005,"手机号或密码错误"),
    USER_NOT_LOGIN(20006,"用户未登录"),
    AUTH_ERROR(20007,"用户认证失败"),
    ACCESS_ERROR(20008,"权限无法获取相应的资源");
    private final int errorCode;
    private final String errorMsg;

    ErrorCode(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
