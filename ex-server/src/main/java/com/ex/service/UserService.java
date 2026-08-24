package com.ex.service;

import com.ex.dto.UserLoginDTO;
import com.ex.entity.User;

public interface UserService {

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User wechatLogin(UserLoginDTO userLoginDTO);
}
