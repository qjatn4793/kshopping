package com.shopping.kshopping.main.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@ResponseBody
public class MainController {

    @GetMapping("/productView")
    public String productView(){



        return "1";
    }


}
