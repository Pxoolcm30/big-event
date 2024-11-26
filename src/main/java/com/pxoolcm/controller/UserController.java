package com.pxoolcm.controller;

import com.pxoolcm.pojo.Result;
import com.pxoolcm.pojo.User;
import com.pxoolcm.service.UserService;
import com.pxoolcm.utils.JwtUtil;
import com.pxoolcm.utils.Md5Util;
import com.pxoolcm.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/7 10:59
 */

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
            //查询用户名

            User u = userService.findByUserName(username);
            if (u == null){
                //用户名没被占用
                //允许注册
                userService.register(username,password);
                return Result.success();
            } else {
                //用户名被占用
                //警告
                return Result.error("用户名已被占用，请输入一个新的用户名！");
            }
    }
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$")String password){
        //查找用户是否存在
        User loginUser = userService.findByUserName(username);
        if (loginUser == null){
            return Result.error("用户不存在");
        }
        //验证密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            //登录成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        //如果走到这说明密码错误
        return Result.error("密码错误");
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        //根据用户名查看用户信息
//        Map<String, Object> user = JwtUtil.parseToken(token);
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User u = userService.findByUserName(username);
        return Result.success(u);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String,String> params){
        //1.手动校验参数
        String old_pwd = params.get("old_pwd");
        String new_pwd = params.get("new_pwd");
        String re_pwd = params.get("re_pwd");

        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd)){
            return Result.error("缺失必要的参数");
        }
        //走到这，说明参数完整，需要检查原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!loginUser.getPassword().equals(Md5Util.getMD5String(old_pwd))){
            return Result.error("原密码错误");
        }
        if (!new_pwd.equals(re_pwd)){
            return Result.error("两次输入的密码不匹配");
        }
        //2.参数校验成功，更新密码
        userService.updatePwd(new_pwd);
        return Result.success();
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.controller
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-07  10:59
 * @Description: TODO
 * @Version: 1.0
 */
