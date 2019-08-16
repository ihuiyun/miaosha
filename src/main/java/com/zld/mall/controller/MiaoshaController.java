package com.zld.mall.controller;

import com.zld.mall.domain.MiaoshaOrder;
import com.zld.mall.domain.MiaoshaUser;
import com.zld.mall.domain.OrderInfo;
import com.zld.mall.result.CodeMsg;
import com.zld.mall.service.GoodsService;
import com.zld.mall.service.MiaoshaService;
import com.zld.mall.service.MiaoshaUserService;
import com.zld.mall.service.OrderService;
import com.zld.mall.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

/**
 * 描述：商品秒杀请求处理控制器
 *
 * @author lida
 * @time 2019/8/16 11:48
 */
@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    MiaoshaUserService userService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    MiaoshaService miaoshaService;

    /**
     * 接收秒杀的请求
     * @param model model
     * @return 跳转路径
     */
    @RequestMapping("/do_miaosha")
    public String login(Model model, MiaoshaUser user, @RequestParam("goodsId")long goodsId) throws ParseException {
        model.addAttribute("user",user);
        if (user == null){
            return "login";
        }
        //判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0){
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_ERROR.getMsg());
            return "miaosha_fail";
        }
        //判断是否秒杀到商品
        MiaoshaOrder miaoshaOrder = orderService.getMiaoshaOrderByUserIdAndGoodsId(user.getId(), goodsId);
        if (miaoshaOrder != null){
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }


}
