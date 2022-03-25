package com.shopping.kshopping.product.controller;

import com.shopping.kshopping.product.service.ProductService;
import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

            if(productService.productView(i) != null){
                productView.put(valueOf(i), productService.productView(i));
            }
        }
        return productView;
    }

    @PostMapping("/product")
    public int productCreate(@RequestBody ProductVo productVo) throws Exception{

        return productService.productCreate(productVo.getProductName());

    }

    @PutMapping("/product")
    public String productUpdate(){

        return null;
    }

    @DeleteMapping("/product")
    public int productDelete(@RequestBody ProductVo productVo) throws Exception{

        return productService.productDelete(productVo.getProductSeq());
    }
}
