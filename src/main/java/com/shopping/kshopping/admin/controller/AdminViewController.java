package com.shopping.kshopping.admin.controller;

import com.shopping.kshopping.admin.service.AdminService;
import com.shopping.kshopping.admin.vo.AdminVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Controller
public class AdminViewController {
    AdminService adminService;

    @GetMapping("/admin")
    public String admin(HttpServletRequest request){
        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginCheck")!=null && session.getAttribute("adminVo")!=null){
            if(session.getAttribute("adminLoginCheck").equals("success")){
                return "/admin/admin.html";
            }
        }

        return "admin/adminLogin.html";
    }

    @PostMapping("/admin")
    public String adminLoginCheck(AdminVo adminVo, HttpServletRequest request, Model model)throws Exception{

        int adminLoginCheck = adminService.adminLoginCheck(adminVo);
        HttpSession session = request.getSession();

        if(adminLoginCheck == 1){
            session.setAttribute("adminLoginCheck", "success");
            session.setAttribute("adminVo", adminVo);

            return "redirect:/admin";
        }else {
            return "redirect:/admin";
        }
    }

    @RequestMapping("/adminLogout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("adminLoginCheck");
        session.removeAttribute("adminVo");
        session.invalidate();
        return "redirect:/admin";
    }

    @RequestMapping("/admin/createProduct")
    public String createProduct(){

        return "/admin/createProduct.html";
    }

    @RequestMapping("/admin/updateProduct")
    public String updateProduct(){

        return "/admin/updateProduct.html";
    }

    @RequestMapping("/admin/deleteProduct")
    public String deleteProduct(){

        return "/admin/deleteProduct.html";
    }

}
