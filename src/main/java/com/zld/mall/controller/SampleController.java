package com.zld.mall.controller;

import com.zld.mall.domain.User;
import com.zld.mall.redis.RedisService;
import com.zld.mall.redis.key.UserKey;
import com.zld.mall.result.CodeMsg;
import com.zld.mall.result.Result;
import com.zld.mall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 描述：控制器
 *
 * @author lida
 * @time 2019/8/13 19:51
 */
@Controller
@RequestMapping("/control")
public class SampleController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/test")
    public String test(Model model){
        model.addAttribute("msg","success");
        return "hello";
    }

    @RequestMapping("/findUserById")
    @ResponseBody
    public Result<User> findUserById(){
        User user = userService.findUserById(1);
        return user == null? Result.error(CodeMsg.SERVER_ERROR):Result.success(user);
    }

    @RequestMapping("/redis/test/set")
    @ResponseBody
    public Result redisTestSet(){
        boolean re = redisService.set(UserKey.getById,"k1","1111");
        return re?Result.success(CodeMsg.SUCCESS):Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/redis/test/get")
    @ResponseBody
    public Result<String> redisTestGet(){
        String re = redisService.get(UserKey.getById,"k1",String.class);
        return re != null?Result.success(re):Result.error(CodeMsg.SERVER_ERROR);
    }
}
