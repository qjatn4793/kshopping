package com.shopping.kshopping.admin.controller;

import com.shopping.kshopping.admin.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;

@Controller
public class TestController {

    @Resource(name = "fileService")
    FileService fileService;

    /*@PostMapping("/admin/product")
    public void boardRegisterAction(MultipartHttpServletRequest multiRequest){
        try {
            fileService.uploadFile(multiRequest);
        }catch (Exception e){
            System.out.println(e);
        }
    }*/
}
