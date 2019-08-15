package com.zld.mall.controller;

import com.zld.mall.result.Result;
import com.zld.mall.service.MiaoshaUserService;
import com.zld.mall.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
    public String login(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> userLogin(HttpServletResponse response, @Valid LoginVo loginVo){
        logger.info(loginVo.toString());
        userService.login(response, loginVo);
        return  Result.success(true);
    }

}
