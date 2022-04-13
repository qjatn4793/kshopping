package com.shopping.kshopping.admin.mapper;

import com.shopping.kshopping.admin.vo.AdminVo;
import com.shopping.kshopping.login.vo.LoginVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AdminMapper {

    int adminLoginCheck(AdminVo adminVo);

    String adminSelectOne(String adminId);

    int adminGetUserCount();

    LoginVo adminGetUser(int userSeq);

    int adminUserDelete(int userSeq);

}
