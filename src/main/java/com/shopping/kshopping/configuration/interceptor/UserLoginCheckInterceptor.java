package com.shopping.kshopping.configuration.interceptor;

import com.shopping.kshopping.configuration.SHA256;
import com.shopping.kshopping.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static java.lang.String.valueOf;

@Slf4j
@Component
public class UserLoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean loginCheck =false;

        HttpSession session = request.getSession();
        if(!StringUtils.isEmpty(session.getAttribute("loginCheck"))){
            if(session.getAttribute("loginCheck").equals("success")){
                if(session.getAttribute("userId") != null && session.getAttribute("userId") != "" && session.getAttribute("userPw") != null && session.getAttribute("userPw") != ""){
                    loginCheck = true;
                }else {
                    response.sendRedirect("/login");
                    session.invalidate();
                }
            }else{
                response.sendRedirect("/login");
                session.invalidate();
            }
        }else{
            response.sendRedirect("/login");
            session.invalidate();
        }

        return loginCheck;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3) throws Exception {

    }
}