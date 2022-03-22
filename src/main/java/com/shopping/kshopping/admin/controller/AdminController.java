package com.shopping.kshopping.admin.controller;

import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;


@AllArgsConstructor
@RestController
public class AdminController {

    @GetMapping("/admin/viewProduct")
    public String viewProduct(ProductVo productVo){

        ArrayList<ProductVo> productVoArrayList = new ArrayList<>();
        for (int i = 0; i < 10; i++ ){
            ProductVo products = new ProductVo();
            /*products.getProductId("productId"+ i);
            products.getProductName("productName"+ i);
            products.getProduct("product"+ i);*/

            productVoArrayList.add(products);
        }

        //productVo.addAttribute("product", products);

        return null;
    }
}
