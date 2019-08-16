package com.zld.mall.dao;

import com.zld.mall.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/16 10:48
 */
@Mapper
@Component
public interface GoodsDao {

    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join " +
            "goods g on mg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select("select g.*,mg.miaosha_price,mg.stock_count,mg.start_date,mg.end_date from miaosha_goods mg left join " +
            "goods g on mg.goods_id = g.id where g.id = ${goodsId}")
    GoodsVo getGoodsVoByGoodsId(@Param(value = "goodsId") long goodsId);
}
