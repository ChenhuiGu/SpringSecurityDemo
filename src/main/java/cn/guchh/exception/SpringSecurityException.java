package cn.guchh.exception;

import lombok.Data;

/**
 * @author chenhuigu
 */

@Data
public class SpringSecurityException extends RuntimeException{
    private final int errorCode;
    private final String errorMsg;

    public SpringSecurityException(int errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public SpringSecurityException(ErrorCode errorCode){
        this.errorCode = errorCode.getErrorCode();
        this.errorMsg = errorCode.getErrorMsg();
    }
}
