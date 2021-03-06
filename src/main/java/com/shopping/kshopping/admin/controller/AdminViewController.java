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

    @GetMapping("/admin")
    public String admin(HttpServletRequest request){
        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginCheck")!=null && session.getAttribute("adminId")!=null && session.getAttribute("adminPw")!=null){
            if(session.getAttribute("adminLoginCheck").equals("success")){
                return "admin/admin.html";
            }
        }

        return "admin/adminLogin.html";
    }

    @GetMapping("/admin/adminProduct")
    public String adminProduct(HttpServletRequest request){

        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginCheck")!=null && session.getAttribute("adminId")!=null && session.getAttribute("adminPw")!=null){
            if(session.getAttribute("adminLoginCheck").equals("success")){
                return "admin/control/adminProduct.html";
            }
        }

        return "admin/control/adminLogin.html";
    }

    @GetMapping("/admin/adminBoard")
    public String adminBoard(HttpServletRequest request){

        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginCheck")!=null && session.getAttribute("adminId")!=null && session.getAttribute("adminPw")!=null){
            if(session.getAttribute("adminLoginCheck").equals("success")){
                return "admin/control/adminBoard.html";
            }
        }

        return "admin/control/adminLogin.html";
    }

    @GetMapping("/admin/adminUser")
    public String adminUser(HttpServletRequest request){

        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginCheck")!=null && session.getAttribute("adminId")!=null && session.getAttribute("adminPw")!=null){
            if(session.getAttribute("adminLoginCheck").equals("success")){
                return "admin/control/adminGetUser.html";
            }
        }

        return "admin/control/adminLogin.html";
    }

    @GetMapping("/admin/adminDesign")
    public String adminDesign(HttpServletRequest request){

        HttpSession session = request.getSession();

        if(session.getAttribute("adminLoginCheck")!=null && session.getAttribute("adminId")!=null && session.getAttribute("adminPw")!=null){
            if(session.getAttribute("adminLoginCheck").equals("success")){
                return "admin/control/adminDesign.html";
            }
        }

        return "admin/adminDesign.html";
    }

}
