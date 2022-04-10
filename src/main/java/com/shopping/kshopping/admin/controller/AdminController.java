package com.shopping.kshopping.admin.controller;

import com.shopping.kshopping.admin.service.AdminService;
import com.shopping.kshopping.admin.vo.AdminVo;
import com.shopping.kshopping.configuration.SHA256;
import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;


@AllArgsConstructor
@ResponseBody
@RestController
public class AdminController {

    /*@GetMapping("/admin/viewProduct")
    public String viewProduct(ProductVo productVo){

        return null;
    }*/

    AdminService adminService;

    @PostMapping("/admin")
    public int adminLoginCheck(@RequestBody AdminVo adminVo, HttpServletRequest request)throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();

        int adminLoginCheck = adminService.adminLoginCheck(adminVo);
        String adminPw = adminService.adminSelectOne(adminVo.getAdminId());
        String encryptAdminPw = "";

        // nullpointexception 처리
        try{
            encryptAdminPw = sha256.encrypt(adminPw);
        }catch (NullPointerException e) {
            adminLoginCheck = 0;
            return adminLoginCheck;
        }

        HttpSession session = request.getSession();

        if(encryptAdminPw.equals(sha256.encrypt(adminVo.getAdminPw()))) {
            if (adminLoginCheck == 1) {
                session.setAttribute("adminLoginCheck", "success");
                session.setAttribute("adminId", adminVo.getAdminId());
                session.setAttribute("adminPw", sha256.encrypt(adminVo.getAdminPw()));

                return adminLoginCheck;
            } else {
                adminLoginCheck = 0;
                return adminLoginCheck;
            }
        }else {
            adminLoginCheck = 0;
            return adminLoginCheck;
        }
    }


    @RequestMapping("/admin")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("adminLoginCheck");
        session.removeAttribute("adminId");
        session.removeAttribute("adminPw");
        session.invalidate();
        return "adminLogout";
    }
}
