package com.zld.mall.controller;

import com.zld.mall.domain.MiaoshaUser;
import com.zld.mall.service.MiaoshaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
     * @param model model
     * @return 跳转路径
     */
    @RequestMapping("/to_list")
    public String login(Model model,MiaoshaUser user){
        model.addAttribute("user",user);
        return "goods_list";
    }
}
