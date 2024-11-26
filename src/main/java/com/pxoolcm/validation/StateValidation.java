package com.pxoolcm.validation;

import com.pxoolcm.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @ClassDescription:
 * @JdkVersion: 2.1
 * @Author: 廖春花
 * @Created: 2024/9/10 14:21
 */
public class StateValidation implements ConstraintValidator<State,String> {
    @Override
    /**
     * @description:
     * @author: Pxoolcm
     * @date: 2024/9/10 14:22
     * @param: [s, constraintValidatorContext]
     * value表示要校验的数据
     * @return: boolean 如果返回false，校验不通过
     **/
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        //提供校验规则
        if (s == null){
            return false;
        }
        if (s.equals("已发布")||s.equals("草稿")){
            return true;
        }
        return false;
    }
}
/**
 * @BelongsProject: big-event
 * @BelongsPackage: com.pxoolcm.validation
 * @Author: Pxoolcm
 * @CreateTime: 2024-09-10  14:21
 * @Description: TODO
 * @Version: 1.0
 */
