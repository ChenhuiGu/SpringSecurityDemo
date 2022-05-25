package cn.guchh.dao;

import cn.guchh.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.annotation.Resource;

@SpringBootTest
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void saveUser(){
        User user = new User();
        user.setMobile("13776858917");
        user.setPassword(passwordEncoder.encode("19921110"));
        userMapper.insert(user);
    }
}
