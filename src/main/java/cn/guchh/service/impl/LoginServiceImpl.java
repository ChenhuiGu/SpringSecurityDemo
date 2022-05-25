package cn.guchh.service.impl;

import cn.guchh.dao.UserMapper;
import cn.guchh.entity.User;
import cn.guchh.exception.ErrorCode;
import cn.guchh.exception.SpringSecurityException;
import cn.guchh.security.entity.LoginUser;
import cn.guchh.security.utils.JwtUtil;
import cn.guchh.security.utils.RedisCache;
import cn.guchh.service.LoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author chenhuigu
 */
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper, User> implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;
    @Override
    public String login(User user) {
        //用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(user.getMobile(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        //未通过认证
        if(Objects.isNull(authenticate)){
            throw new SpringSecurityException(ErrorCode.MOBILE_OR_PASSWD_ERROR);
        }
        //Create jwt
        // 以mobile作为用户id,熵增大，不建议
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //save userInfo in redis
        redisCache.setCacheObject("login"+userId,loginUser);
        return jwt;
    }

    @Override
    public void logout() {
        //获取userId
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        //清除redis缓存
        redisCache.deleteObject("login" + userId);
    }

}
