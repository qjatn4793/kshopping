package com.shopping.kshopping.login.service;

import com.shopping.kshopping.login.mapper.LoginMapper;
import com.shopping.kshopping.login.vo.LoginVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Service("loginService")
public class LoginService {

    LoginMapper loginMapper;

    public int loginCheck(LoginVo loginVo){

        return loginMapper.loginCheck(loginVo);
    }

    public String userSelectOne(String userId){

        return loginMapper.userSelectOne(userId);
    }

    public int userIdCheck(LoginVo loginVo){

        return loginMapper.userIdCheck(loginVo);
    }

    public int userRegister(LoginVo loginVo){

        return loginMapper.userRegister(loginVo);
    }

}
