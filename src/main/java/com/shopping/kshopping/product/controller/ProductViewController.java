package com.shopping.kshopping.product.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class ProductViewController {

    @GetMapping("/detailProduct/{productSeq}")
    public String details(@PathVariable("productSeq") int productSeq) throws Exception{

        //productSeq

        return "product/detailProduct.html";
    }

}
