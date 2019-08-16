package com.zld.mall.service;

import com.zld.mall.dao.GoodsDao;
import com.zld.mall.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 描述：Goods对应的Service类
 *
 * @author lida
 * @time 2019/8/13 21:12
 */
@Service
public class GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    public List<GoodsVo> listGoodsVo(){
        return goodsDao.listGoodsVo();
    }
}
