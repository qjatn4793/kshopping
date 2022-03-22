package com.shopping.kshopping.login.controller;

import com.shopping.kshopping.login.service.LoginService;
import com.shopping.kshopping.login.vo.LoginVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Controller
public class LoginViewController {

    LoginService loginService;

    @GetMapping("/login")
    public String login(){

        return "login/login.html";
    }

    @PostMapping("/login")
    public String loginCheck(LoginVo loginVo, HttpServletRequest request){

        int loginCheck = loginService.loginCheck(loginVo);
        HttpSession session = request.getSession();

        if(loginCheck == 1){
            session.setAttribute("loginCheck", "success");
            session.setAttribute("loginVo", loginVo);

            return "redirect:/";
        }else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/userLogout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("loginCheck");
        session.removeAttribute("loginVo");
        session.invalidate();
        return "redirect:/";
    }
}
