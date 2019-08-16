package com.zld.mall.service;

import com.zld.mall.dao.OrderDao;
import com.zld.mall.domain.MiaoshaOrder;
import com.zld.mall.domain.MiaoshaUser;
import com.zld.mall.domain.OrderInfo;
import com.zld.mall.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述：订单业务处理
 *
 * @author lida
 * @time 2019/8/16 14:21
 */
@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;

    /**
     * 根据用户id和商品id获取秒杀的订单
     * @param userId 用户id
     * @param goodsId 商品id
     * @return 秒杀的订单
     */
    public MiaoshaOrder getMiaoshaOrderByUserIdAndGoodsId(Long userId, long goodsId) {
        return orderDao.getMiaoshaOrderByUserIdAndGoodsId(userId, goodsId);
    }

    /**
     * 事务控制的秒杀的下订单和创建秒杀订单
     * @param user user信息
     * @param goods 商品信息
     * @return 订单信息
     */
    @Transactional
    public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) throws ParseException {
        //时间处理,获取下单时间
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sf.format(new Date());
        Date date = sf.parse(format);
        //创建订单
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCreateDate(date);
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());
        //下订单
        long orderId = orderDao.insert(orderInfo);
        MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
        miaoshaOrder.setGoodsId(goods.getId());
        miaoshaOrder.setOrderId(orderId);
        miaoshaOrder.setUserId(user.getId());
        orderDao.insertMiaoshaOrder(miaoshaOrder);
        return orderInfo;
    }
}
