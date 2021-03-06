package com.shopping.kshopping.login.mapper;

import com.shopping.kshopping.login.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {

    int loginCheck(LoginVo loginVo);

    String userSelectOne(String userId);

    int userIdCheck(LoginVo loginVo);

    int userRegister(LoginVo loginVo);

    LoginVo userInfo(String userId);

    int userUpdate(LoginVo loginVo);
    
}
