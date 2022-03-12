package com.shopping.kshopping.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Controller
public class AdminViewController {

    @GetMapping("/admin")
    public String admin(){

        return "admin/admin.html";
    }

}
