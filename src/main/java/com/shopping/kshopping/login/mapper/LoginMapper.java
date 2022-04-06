package com.shopping.kshopping.login.mapper;

import com.shopping.kshopping.login.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface LoginMapper {

    int loginCheck(LoginVo loginVo);

    String userSelectOne(String userId);
    
}
