package com.shopping.kshopping.craw.controller;

import com.shopping.kshopping.craw.service.CrawService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
@ResponseBody
public class CrawController {
    CrawService crawService;

}
