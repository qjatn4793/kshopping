package com.shopping.kshopping.login.controller;

import com.shopping.kshopping.login.service.LoginService;
import com.shopping.kshopping.login.vo.LoginVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@AllArgsConstructor
@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String login(HttpServletRequest request){

        HttpSession session = request.getSession();

        if(session.getAttribute("loginCheck") == "success"){
            return "/main/main.html";
        }else {
            session.removeAttribute("loginCheck");
            session.removeAttribute("userId");
            return "login/login.html";
        }
    }
}
