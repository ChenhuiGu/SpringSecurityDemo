package cn.guchh.controller;

import cn.guchh.entity.User;
import cn.guchh.service.LoginService;
import cn.guchh.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenhuigu
 */
@RestController
@RequestMapping("/user")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public R login(@RequestBody User user) {
        String jwt = loginService.login(user);
        return R.ok().data("token", jwt);
    }

    @GetMapping("logout")
    public R logout(){
        //校验成功
        loginService.logout();
        return R.ok().message("注销成功");
    }

    @GetMapping("hello")
    @PreAuthorize("hasAnyAuthority('sys:delete')")
    public R hello(){
        return R.ok().data("item","hello");
    }
}
