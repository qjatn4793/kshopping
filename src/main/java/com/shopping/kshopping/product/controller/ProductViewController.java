package com.shopping.kshopping.product.controller;

import com.shopping.kshopping.product.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
public class ProductViewController {

    ProductService productService;

    @GetMapping("/detailProduct/{productSeq}")
    public String details(@PathVariable("productSeq") int productSeq) throws Exception{

        return "product/detailProduct.html";
    }

    @RequestMapping("/mainProduct")
    public String product(){

        return "product/product.html";
    }

}
