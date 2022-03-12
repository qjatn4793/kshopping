package com.shopping.kshopping.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class AdminController {

    @PostMapping("/admin")
    public String admin(){




        return null;
    }
}
