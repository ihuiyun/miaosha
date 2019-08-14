package com.zld.mall.dao;

import com.zld.mall.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 16:21
 */
@Mapper
@Component
public interface MiaoshaDao {

    @Select("select * from miaosha_user where id = #{id}")
    public MiaoshaUser getById(@Param("id") Long id);
}
