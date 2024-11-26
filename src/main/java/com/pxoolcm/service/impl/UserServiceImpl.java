package com.pxoolcm.service.impl;

import com.pxoolcm.mapper.UserMapper;
import com.pxoolcm.pojo.User;
import com.pxoolcm.service.UserService;
import com.pxoolcm.utils.Md5Util;
import com.pxoolcm.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/7 10:59
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username) {
        //到UserMapper中实现查找用户名的方法

        User u = userMapper.findByUserName(username);

        return u;
    }

    @Override
    public void register(String username, String password) {
        //密码需要加密处理
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username,md5String);
    }

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatar) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatar,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        //新密码需要加密处理

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.service.impl
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-07  10:59
 * @Description: TODO
 * @Version: 1.0
 */
