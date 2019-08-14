package com.zld.mall.volidator;

import com.zld.mall.util.VolidatorUtil;
import org.thymeleaf.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 17:08
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String >{
    private boolean required = false;
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required){
            return VolidatorUtil.isMobile(value);
        }else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return VolidatorUtil.isMobile(value);
            }
        }
    }
}
