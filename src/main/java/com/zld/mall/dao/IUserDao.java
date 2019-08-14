package com.zld.mall.dao;

import com.zld.mall.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 描述：Dao层的方法接口
 *
 * @author lida
 * @time 2019/8/13 21:06
 */
@Mapper
@Component
public interface IUserDao {

    @Select("select * from user where id = #{id}")
    User findUserById(@Param("id") int id);

    @Insert("insert into user(name) values(#{name})")
    int insertUser(@Param("name") String name);
}
