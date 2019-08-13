package com.zld.mall.service;

import com.zld.mall.dao.IUserDao;
import com.zld.mall.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/13 21:12
 */
@Service
public class UserService {

    @Autowired
    private IUserDao userDao;

    public User findUserById(int id){
        return userDao.findUserById(id);
    }
}
