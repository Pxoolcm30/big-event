package com.pxoolcm.interceptors;

import com.pxoolcm.pojo.Result;
import com.pxoolcm.utils.JwtUtil;
import com.pxoolcm.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/8 9:52
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Map<String, Object> claims = JwtUtil.parseToken(token);
            //ThreadLocal来把数据存在线程中，该线程是全局的
            ThreadLocalUtil.set(claims);
            return true;
        } catch (Exception e) {
            response.setStatus(401);
            return false;
        }
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.interceptors
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-08  09:52
 * @Description: TODO
 * @Version: 1.0
 */
