package com.shopping.kshopping.admin.controller;

import com.shopping.kshopping.admin.service.AdminService;
import com.shopping.kshopping.admin.vo.AdminVo;
import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    public int adminLoginCheck(@RequestBody AdminVo adminVo, HttpServletRequest request)throws Exception{

        int adminLoginCheck = adminService.adminLoginCheck(adminVo);
        HttpSession session = request.getSession();

        if(adminLoginCheck == 1){
            session.setAttribute("adminLoginCheck", "success");
            session.setAttribute("adminVo", adminVo);

            return adminLoginCheck;
        }else {
            return adminLoginCheck;
        }
    }


    @RequestMapping("/admin")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("adminLoginCheck");
        session.removeAttribute("adminVo");
        session.invalidate();
        return "adminLogout";
    }
}
