package com.shopping.kshopping.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class ProductViewController {

    @GetMapping("/detailProduct")
    public String details(){

        return "main/detailProduct.html";
    }

}
