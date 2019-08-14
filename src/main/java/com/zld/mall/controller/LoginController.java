package com.zld.mall.controller;

import com.zld.mall.result.CodeMsg;
import com.zld.mall.result.Result;
import com.zld.mall.service.MiaoshaUserService;
import com.zld.mall.util.VolidatorUtil;
import com.zld.mall.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：控制器
 *
 * @author lida
 * @time 2019/8/13 19:51
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    MiaoshaUserService userService;

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String test(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> findUserById(LoginVo loginVo){
        logger.info(loginVo.toString());
        //做参数校验
        String inputMobile = loginVo.getMobile();
        String inputPass = loginVo.getPassword();
        if (inputMobile == null) {
            return Result.error(CodeMsg.MOBILE_EMPTY);
        }
        if (inputPass == null) {
            return Result.error(CodeMsg.PASSWORD_EMPTY);
        }
        if (!VolidatorUtil.isMobile(inputMobile)){
            return Result.error(CodeMsg.MOBILE_ERROR);
        }
        //登录
        CodeMsg cm = userService.login(loginVo);
        return cm.getCode() == 0? Result.success(true):Result.error(cm);
    }

}
