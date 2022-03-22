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

}
