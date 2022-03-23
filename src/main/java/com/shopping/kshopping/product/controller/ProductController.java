package com.shopping.kshopping.product.controller;

import com.shopping.kshopping.product.service.ProductService;
import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@RestController
@ResponseBody
public class ProductController {

    ProductService productService;

    @GetMapping("/product")
    public ProductVo productView() throws Exception{

        ArrayList<String> productView = new ArrayList();



        for (int i=0; productService.productCount() > i; i++) {


            //productView.add();
        }


        return productService.productView(1);
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
