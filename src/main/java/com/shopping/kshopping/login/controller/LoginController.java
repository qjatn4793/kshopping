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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.valueOf;

@AllArgsConstructor
@RestController
@ResponseBody
public class LoginController {

    LoginService loginService;


    @PostMapping("/login")
    public int loginCheck(@RequestBody LoginVo loginVo, HttpServletRequest request) throws NoSuchAlgorithmException {
        SHA256 sha256 = new SHA256();
        String encryptUserPw = "";
        HttpSession session = request.getSession();
        int loginCheck = 0;
        String userPw = loginService.userSelectOne(loginVo.getUserId());


        try{ // nullpointexception 처리
            encryptUserPw = sha256.encrypt(userPw);
        }catch (NullPointerException e) {
            loginCheck = 0;
            return loginCheck;
        }

        if(encryptUserPw.equals(sha256.encrypt(loginVo.getUserPw()))) {
            loginCheck = loginService.loginCheck(loginVo);

            if (loginCheck == 1) {

                loginVo = loginService.userInfo(loginVo.getUserId());

                session.setAttribute("loginCheck", "success");
                session.setAttribute("userId", loginVo.getUserId());
                session.setAttribute("userPw", sha256.encrypt(loginVo.getUserPw()));
                session.setAttribute("userName", loginVo.getUserName());

                String userBirth = loginVo.getUserBirth();
                userBirth = userBirth.replace("-", "");

                session.setAttribute("userBirth", userBirth);
                session.setAttribute("userPhone", loginVo.getUserPhone());

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

    @PostMapping("/userIdCheck")
    public int userIdCheck(HttpServletRequest request, @RequestBody LoginVo loginVo){

        String userId = loginVo.getUserId();

        final String checkString = "[a-zA-Z0-9ㄱ-힣\\s]"; // 특수문자 체크

        Matcher matchTest2;
        matchTest2 = Pattern.compile(checkString).matcher(userId); // ID 공백 포함 특수문자 없으면 true

        if (matchTest2.find() == true) {
            return loginService.userIdCheck(loginVo);
        }else {
            return 0;
        }
    }

    @PostMapping("/userRegister")
    public int userRegister(HttpServletRequest request, @RequestBody LoginVo loginVo){

        String userId = loginVo.getUserId();
        String userBirth = loginVo.getUserBirth();
        String userName = loginVo.getUserName();
        String userPhone = loginVo.getUserPhone();

        final String checkNum = "[0-9]+"; // 숫자만 있는지 체크
        final String checkString = "[a-zA-Z0-9ㄱ-힣\\s]"; // 특수문자 체크

        Matcher matchTest;
        Matcher matchTest2;
        matchTest = Pattern.compile(checkString).matcher(userName); // 이름 공백 포함 특수문자가 없으면 true
        matchTest2 = Pattern.compile(checkString).matcher(userId); // ID 공백 포함 특수문자 없으면 true

        if (Pattern.matches(checkNum, userBirth) && Pattern.matches(checkNum, userPhone)) { // 생년월일 숫자만 있는지 체크 휴대폰번호 숫자만 있는지 체크
            if (matchTest.find() == true && matchTest2.find() == true){ //이름 공백 포함 특수문자가 없으면 true ID 공백 포함 특수문자 없으면 true
                return loginService.userRegister(loginVo);
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }

    @PutMapping("/userRegister")
    public int userUpdate(HttpServletRequest request, @RequestBody LoginVo loginVo){

        String userId = loginVo.getUserId();
        String userBirth = loginVo.getUserBirth();
        String userName = loginVo.getUserName();
        String userPhone = loginVo.getUserPhone();

        final String checkNum = "[0-9]+"; // 숫자만 있는지 체크
        final String checkString = "[a-zA-Z0-9ㄱ-힣\\s]"; // 특수문자 체크

        Matcher matchTest;
        Matcher matchTest2;
        matchTest = Pattern.compile(checkString).matcher(userName); // 이름 공백 포함 특수문자가 없으면 true
        matchTest2 = Pattern.compile(checkString).matcher(userId); // ID 공백 포함 특수문자 없으면 true

        if (Pattern.matches(checkNum, userBirth) && Pattern.matches(checkNum, userPhone)) { // 생년월일 숫자만 있는지 체크 휴대폰번호 숫자만 있는지 체크
            if (matchTest.find() == true && matchTest2.find() == true){ //이름 공백 포함 특수문자가 없으면 true ID 공백 포함 특수문자 없으면 true
                int userUpdate = loginService.userUpdate(loginVo);

                if(userUpdate == 1){
                    HttpSession session = request.getSession();
                    session.removeAttribute("loginCheck");
                    session.removeAttribute("userId");
                    session.invalidate();
                    return userUpdate;
                }else {
                    return 0;
                }
            }else {
                return 0;
            }
        }else {
            return 0;
        }
    }
}
