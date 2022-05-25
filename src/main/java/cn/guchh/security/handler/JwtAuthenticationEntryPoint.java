package cn.guchh.security.handler;

import cn.guchh.exception.ErrorCode;
import cn.guchh.security.utils.WebUtils;
import cn.guchh.utils.R;
import com.alibaba.fastjson.JSON;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenhuigu
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String json = JSON.toJSONString(R.setResult(ErrorCode.AUTH_ERROR));
        WebUtils.renderString(response,json);
    }
}
