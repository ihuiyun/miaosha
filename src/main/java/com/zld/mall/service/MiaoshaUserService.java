package com.zld.mall.service;

import com.zld.mall.dao.MiaoshaDao;
import com.zld.mall.domain.MiaoshaUser;
import com.zld.mall.result.CodeMsg;
import com.zld.mall.result.Result;
import com.zld.mall.util.MD5Util;
import com.zld.mall.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：
 *
 * @author lida
 * @time 2019/8/14 16:23
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaDao miaoshaDao;

    public MiaoshaUser getById(Long id){
        return miaoshaDao.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if(loginVo == null)
            return CodeMsg.SERVER_ERROR;
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            return CodeMsg.MOBILE_NOTEXEITS;
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();

        String calcPass = MD5Util.fromPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)){
            return CodeMsg.PASSWORD_ERROR;
        }
        return CodeMsg.SUCCESS;
    }
}
