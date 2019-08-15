package com.zld.mall.controller;

import com.zld.mall.domain.MiaoshaUser;
import com.zld.mall.domain.User;
import com.zld.mall.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * 描述：控制器
 *
 * @author lida
 * @time 2019/8/13 19:51
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    MiaoshaUserService userService;

    /**
     * 登录后的跳转请求
     * @param response http response
     * @param model model
     * @param cookieToken web端 cookie token
     * @param paramToken 移动端 header参数token
     * @return 跳转路径
     */
    @RequestMapping("/to_list")
    public String login(HttpServletResponse response,
                        Model model,
                        @CookieValue(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String cookieToken,
                        @RequestParam(value = MiaoshaUserService.COOKIE_NAME_TOKEN, required = false) String paramToken){
        //没有登录的token则返回登录
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
            return "login";
        }
        //优先取cookie的token
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        //获取用户信息
        MiaoshaUser user = userService.getByToken(response, token);
        model.addAttribute("user",user);
        return "goods_list";
    }
}
