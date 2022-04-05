package com.shopping.kshopping.configuration;

import com.shopping.kshopping.configuration.interceptor.LoginCheckInterceptor;
import com.shopping.kshopping.configuration.interceptor.UserLoginCheckInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@AllArgsConstructor
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    LoginCheckInterceptor loginCheckInterceptor;
    UserLoginCheckInterceptor userLoginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/admin/*")
                .excludePathPatterns("/");

        registry.addInterceptor(userLoginCheckInterceptor)
                .addPathPatterns("/createBoard")
                .addPathPatterns("/createBoardReply")
                .addPathPatterns("/createProductReply");
    }
}
