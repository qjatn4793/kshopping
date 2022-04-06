package com.shopping.kshopping.admin.service;

import com.shopping.kshopping.admin.mapper.AdminMapper;
import com.shopping.kshopping.admin.vo.AdminVo;
import com.shopping.kshopping.util.SessionUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Transactional
@Service("adminService")
public class AdminService {

    AdminMapper adminMapper;

    public int adminLoginCheck(AdminVo adminVo){

        return adminMapper.adminLoginCheck(adminVo);
    }

    public String adminSelectOne(String adminId){

        return adminMapper.adminSelectOne(adminId);
    }

}
