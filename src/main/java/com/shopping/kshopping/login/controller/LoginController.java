package com.shopping.kshopping.login.controller;

import com.shopping.kshopping.login.service.LoginService;
import com.shopping.kshopping.login.vo.LoginVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@RestController
@ResponseBody
public class LoginController {

    LoginService loginService;

    @PostMapping("/login")
    public int loginCheck(@RequestBody LoginVo loginVo, HttpServletRequest request){

        int loginCheck = loginService.loginCheck(loginVo);
        HttpSession session = request.getSession();

        if(loginCheck == 1){
            session.setAttribute("loginCheck", "success");
            session.setAttribute("loginVo", loginVo);
            session.setAttribute("userId", loginVo.getUserId());

            return loginCheck;
        }else {
            return loginCheck;
        }
    }

    @DeleteMapping("/login")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.removeAttribute("loginCheck");
        session.removeAttribute("loginVo");
        session.removeAttribute("userId");
        session.invalidate();

        return "logout";
    }
}
