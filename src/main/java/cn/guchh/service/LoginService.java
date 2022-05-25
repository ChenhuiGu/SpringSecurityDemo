package cn.guchh.service;

import cn.guchh.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author chenhuigu
 */
public interface LoginService extends IService<User> {
    String login(User user);

    void logout();
}
