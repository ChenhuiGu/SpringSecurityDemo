package cn.guchh.security.handler;

import cn.guchh.exception.ErrorCode;
import cn.guchh.security.utils.WebUtils;
import cn.guchh.utils.R;
import com.alibaba.fastjson.JSON;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenhuigu
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        String json = JSON.toJSONString(R.setResult(ErrorCode.ACCESS_ERROR));
        WebUtils.renderString(response,json);
    }
}

