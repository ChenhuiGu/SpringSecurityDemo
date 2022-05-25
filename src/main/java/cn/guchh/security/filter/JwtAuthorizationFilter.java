package cn.guchh.security.filter;

import cn.guchh.exception.ErrorCode;
import cn.guchh.exception.SpringSecurityException;
import cn.guchh.security.entity.LoginUser;
import cn.guchh.security.utils.JwtUtil;
import cn.guchh.security.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author chenhuigu
 */
@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private RedisCache redisCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取token
        String token = request.getHeader("token");
        //token为空
        if(!StringUtils.hasText(token)){
            // 放行
            filterChain.doFilter(request,response);
            return;
        }
        //解析token
        String userId;
        try {
            userId = JwtUtil.parseJWT(token).getSubject();
        } catch (Exception e) {
            throw new SpringSecurityException(ErrorCode.VERIFY_JWT_FAILED);
        }
        //获取用户信息，
        LoginUser loginUser = redisCache.getCacheObject("login" + userId);
        if(Objects.isNull(loginUser)){
            throw new SpringSecurityException(ErrorCode.USER_NOT_LOGIN);
        }
        //封装对象,添加权限信息
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request,response);
    }
}
