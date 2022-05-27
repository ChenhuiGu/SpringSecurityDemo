package cn.guchh.security.service.impl;

import cn.guchh.dao.MenuMapper;
import cn.guchh.dao.UserMapper;
import cn.guchh.entity.User;
import cn.guchh.exception.ErrorCode;
import cn.guchh.exception.SpringSecurityException;
import cn.guchh.security.entity.LoginUser;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author chenhuigu
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        //查询UserInfo
        LambdaQueryWrapper<User> query = new LambdaQueryWrapper<>();
        query.eq(User::getMobile,mobile);
        User user = userMapper.selectOne(query);
        if(Objects.isNull(user)){
            throw new SpringSecurityException(ErrorCode.MOBILE_IS_NOT_EXIST);
        }
        //查询权限
        List<String> permissions = menuMapper.selectMenuByUserId(user.getId());
        //封装对象
        return new LoginUser(user,permissions);
    }
}