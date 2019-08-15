package com.zld.mall.service;

import com.zld.mall.dao.MiaoshaDao;
import com.zld.mall.domain.MiaoshaUser;
import com.zld.mall.exception.GlobalException;
import com.zld.mall.redis.RedisService;
import com.zld.mall.redis.key.MiaoShaUserKey;
import com.zld.mall.result.CodeMsg;
import com.zld.mall.util.MD5Util;
import com.zld.mall.util.UUIDUtil;
import com.zld.mall.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * 描述：user的业务处理类
 *
 * @author lida
 * @time 2019/8/14 16:23
 */
@Service
public class MiaoshaUserService {
    public static final String COOKIE_NAME_TOKEN = "token";

    @Autowired
    MiaoshaDao miaoshaDao;

    @Autowired
    RedisService redisService;

    /**
     * 从数据库根据id获取用户信息
     * @param id
     * @return user信息
     */
    public MiaoshaUser getById(Long id){
        return miaoshaDao.getById(id);
    }

    /**
     * 从缓存根据user的cookie中的token得到用户的信息
     * @param token
     * @return user信息
     */
    public MiaoshaUser getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token)){
            return null;
        }
        //从缓存中获取user信息
        MiaoshaUser user = redisService.get(MiaoShaUserKey.token, token, MiaoshaUser.class);
        //延长登录的有效期
        if(user != null)
            addCookie(response, user);
        return user;
    }

    /**
     * 登录的用户验证
     * @param response
     * @param loginVo
     * @return 是否成功登录
     */
    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null)
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOTEXEITS);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();

        String calcPass = MD5Util.fromPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        addCookie(response, user);
        return true;
    }

    private void addCookie(HttpServletResponse response, MiaoshaUser user){
        String token = UUIDUtil.uuid();
        //将token和对应的用户信息放入redis缓存
        redisService.set(MiaoShaUserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        //设置cookie有效期与缓存中的token一致
        cookie.setMaxAge(MiaoShaUserKey.token.expireSeconds());
        //设置cookie的PATH为网站的根目录
        cookie.setPath("/");
        //将cookie返回
        response.addCookie(cookie);
    }
}
