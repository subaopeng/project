package com.evan.wj.controller;

import com.evan.wj.entity.AdminMenu;
import com.evan.wj.entity.Result;
import com.evan.wj.entity.User;
import com.evan.wj.service.Impl.AdminMenuServiceImpl;
import com.evan.wj.service.Impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    AdminMenuServiceImpl adminMenuService;

    @CrossOrigin
    @RequestMapping("api/login")
    @ResponseBody
    public Result loginController(@RequestBody User requestUser){

//        String username = requestUser.getUsername();
//        String password = requestUser.getPassword();
        Subject subject = SecurityUtils.getSubject();
//        User user = userService.get(username,password);
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(requestUser.getUsername(), requestUser.getPassword());
//        usernamePasswordToken.setRememberMe(true);
        try{
            subject.login(usernamePasswordToken);
            return new Result(200);
        }catch(Exception e){
            String message = "账号或密码错误!!";
            return new Result(400,message);
        }
//        if(user != null  &&  !user.equals("")){
////            session.setAttribute("user",user);
//            return new Result(200);
//        }else{
//            System.out.println("账号或密码错误！！");
//            return new Result(400);
//        }
    }

    @CrossOrigin
    @PostMapping("api/register")
    @ResponseBody
    public Result register(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if (exist) {
            String message = "用户名已被使用";
            return new Result(400,message);
        }

        // 生成盐,默认长度 16 位
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        // 设置 hash 算法迭代次数
        int times = 2;
        // 得到 hash 后的密码
        String encodedPassword = new SimpleHash("md5", password, salt, times).toString();
        // 存储用户信息，包括 salt 与 hash 后的密码
        user.setSalt(salt);
        user.setPassword(encodedPassword);
        userService.save(user);

        return new Result(200);
    }



    @ResponseBody
    @CrossOrigin
    @GetMapping("api/logout")
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "成功登出";
        return new Result(200,message);
    }


    @ResponseBody
    @CrossOrigin
    @GetMapping(value = "api/authentication")
    public String authentication(){
        return "身份认证成功";
    }


    @GetMapping("/api/menu")
    @CrossOrigin
    @ResponseBody
    public List<AdminMenu> menu() {
        return adminMenuService.getMenusByCurrentUser();
    }

}
