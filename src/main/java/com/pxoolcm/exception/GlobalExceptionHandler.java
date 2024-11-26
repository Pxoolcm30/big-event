package com.pxoolcm.exception;

import com.pxoolcm.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/7 15:05
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage()) ? e.getMessage() : "操作失败");
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.exception
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-07  15:05
 * @Description: TODO
 * @Version: 1.0
 */
