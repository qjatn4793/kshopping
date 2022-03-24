package com.shopping.kshopping.product.controller;

import com.shopping.kshopping.product.service.ProductService;
import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static java.lang.String.valueOf;

@AllArgsConstructor
@RestController
@ResponseBody
public class ProductController {

    ProductService productService;

    @GetMapping("/product")
    public HashMap<String, ProductVo> productView() throws Exception{

        HashMap<String, ProductVo> productView = new HashMap<>();

        int productCount = productService.productCount();

        for (int i=1; productCount >= i; i++) {

            //System.out.println(productService.productView(i));

            productView.put(valueOf(i), productService.productView(i));
        }
        return productView;
    }

    @PostMapping("/product")
    public String productCreate(){

        return null;
    }

    @PutMapping("/product")
    public String productUpdate(){

        return null;
    }

    @DeleteMapping("/product")
    public String productDelete(){

        return null;
    }
}
