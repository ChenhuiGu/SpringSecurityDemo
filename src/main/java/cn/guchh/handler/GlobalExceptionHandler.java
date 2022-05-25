package cn.guchh.handler;

import cn.guchh.exception.SpringSecurityException;
import cn.guchh.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author chenhuigu
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 未知错误
     */
    //@ExceptionHandler(Exception.class)
    //@ResponseBody
    //public R error(Exception e){
    //    //日志
    //    log.error("error:",e);
    //    return R.error();
    //}

    @ExceptionHandler(SpringSecurityException.class)
    @ResponseBody
    public R error(SpringSecurityException e){
        //日志
        return R.error().message(e.getErrorMsg()).code(e.getErrorCode());
    }


}
