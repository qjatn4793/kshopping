package com.shopping.kshopping.craw.controller;

import com.shopping.kshopping.craw.service.CrawService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
public class CrawViewController {
    CrawService crawService;

    @GetMapping("/mainCraw")
    public String craw(){

        return "craw/craw.html";
    }
}
