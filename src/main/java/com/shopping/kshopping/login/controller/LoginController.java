package com.shopping.kshopping.login.controller;

import com.shopping.kshopping.board.service.BoardService;
import com.shopping.kshopping.configuration.SHA256;
import com.shopping.kshopping.login.service.LoginService;
import com.shopping.kshopping.login.vo.LoginVo;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

import static java.lang.String.valueOf;

@AllArgsConstructor
@RestController
@ResponseBody
public class LoginController {

    LoginService loginService;


    @PostMapping("/login")
    public int loginCheck(@RequestBody LoginVo loginVo, HttpServletRequest request, Model model) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();

        int loginCheck = loginService.loginCheck(loginVo);
        String userPw = loginService.userSelectOne(loginVo.getUserId());
        String encryptUserPw = "";

        // nullpointexception 처리
        try{
            encryptUserPw = sha256.encrypt(userPw);
        }catch (NullPointerException e) {
            loginCheck = 0;
            return loginCheck;
        }

        HttpSession session = request.getSession();

        if(encryptUserPw.equals(sha256.encrypt(loginVo.getUserPw()))) {
            if (loginCheck == 1) {
                session.setAttribute("loginCheck", "success");
                session.setAttribute("userId", loginVo.getUserId());
                session.setAttribute("userPw", sha256.encrypt(loginVo.getUserPw()));

                return loginCheck;
            } else {
                loginCheck = 0;
                return loginCheck;
            }
        }else {
            loginCheck = 0;
            return loginCheck;
        }
    }

    @DeleteMapping("/login")
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession();
        session.removeAttribute("loginCheck");
        session.removeAttribute("userId");
        session.invalidate();

        return "logout";
    }
}
