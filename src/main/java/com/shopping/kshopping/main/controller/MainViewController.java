package com.shopping.kshopping.main.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class MainViewController {

    @RequestMapping("/")
    public String main(){

        return "main/main.html";
    }

}
