package com.zld.mall.controller;

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
@RequestMapping("/control")
public class SampleController {

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","success");
        return "hello";
    }
}
