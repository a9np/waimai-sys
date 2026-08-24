package com.ex.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ex.constant.MessageConstant;
import com.ex.dto.UserLoginDTO;
import com.ex.entity.User;
import com.ex.exception.LoginFailedException;
import com.ex.mapper.UserMapper;
import com.ex.properties.WeChatProperties;
import com.ex.service.UserService;
import com.ex.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private WeChatProperties weChatProperties;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User wechatLogin(UserLoginDTO userLoginDTO) {
        //没有openid代表登录失败，抛出异常
        String openid = getUserOpenid(userLoginDTO.getCode());
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        //查看数据库里有没有当前登录的用户
        User user = userMapper.getByOpenId(openid);
        //如果没有新增一个，有就直接返回当前登录user
        if (user == null) {
            user = User.builder()
                    .openid(openid)
                    .createTime(LocalDateTime.now())
                    .build();
            userMapper.insert(user);
        }
        return user;
    }

    private String getUserOpenid(String code) {
        //        构造请求体
        HashMap<String, String> params = new HashMap<>();
        params.put("appid", weChatProperties.getAppid());
        params.put("secret", weChatProperties.getSecret());
        params.put("js_code", code);
        params.put("grant_type", "authorization_code");
        //发起请求
        String json = HttpClientUtil.doGet(WX_LOGIN_URL, params);
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.getString("openid");
    }
}
