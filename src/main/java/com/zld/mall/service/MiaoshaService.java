package com.zld.mall.service;

import com.zld.mall.domain.Goods;
import com.zld.mall.domain.MiaoshaUser;
import com.zld.mall.domain.OrderInfo;
import com.zld.mall.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

/**
 * 描述：秒杀相关的业务处理
 *
 * @author lida
 * @time 2019/8/16 14:28
 */
@Service
public class MiaoshaService {

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    /**
     * 事务管理的减库存 下订单 写入秒杀订单
     * @param user 用户
     * @param goods 下单商品
     * @return 订单
     */
    @Transactional
    public OrderInfo miaosha(MiaoshaUser user, GoodsVo goods) throws ParseException {
        //减库存 下订单 写入秒杀订单
        //减库存
        goodsService.reduceStock(goods);
        //下订单  写入秒杀订单
        return orderService.createOrder(user, goods);
    }
}
