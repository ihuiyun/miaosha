package com.zld.mall.exception;

import com.zld.mall.result.CodeMsg;
import com.zld.mall.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 17:34
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(HttpServletRequest request, Exception e){
        if (e instanceof GlobalException){
            GlobalException globalException = (GlobalException)e;
            return Result.error(globalException.getCodeMsg());
        }else if (e instanceof BindException){
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();

            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else
            return Result.error(CodeMsg.SERVER_ERROR);
    }
}
