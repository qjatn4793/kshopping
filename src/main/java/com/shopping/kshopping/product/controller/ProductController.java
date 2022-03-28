package com.shopping.kshopping.product.controller;

import com.shopping.kshopping.product.service.ProductService;
import com.shopping.kshopping.product.vo.ProductVo;
import com.shopping.kshopping.util.UploadFileUtils;
import lombok.AllArgsConstructor;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;

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

        String path = System.getProperty("user.dir"); // 현재 경로 > C:\Users\PHS-SECURUS\Desktop\kshopping
        // TODO : 프로젝트 경로가 바뀌면 상기 경로 확인 후 변경해줘야함

        //File to Multipartfile
        File file = new File(productVo.getProductImg()); // String to File
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", IOUtils.toByteArray(input));
        //여기까지
        String imgUploadPath = path + "\\src\\main\\resources\\static\\common\\img" + File.separator + "imgUpload";
        String ymdPath = UploadFileUtils.calcPath(imgUploadPath);
        String fileName = null;

        if(multipartFile != null){
            fileName = UploadFileUtils.fileUpload(imgUploadPath, multipartFile.getOriginalFilename(), multipartFile.getBytes(), ymdPath);
        }else {
            fileName = path + "\\src\\main\\resources\\static\\common\\img" + File.separator + "images" + File.separator + "none.png";
        }

        productVo.setProductImg(File.separator + "imgUpload" + ymdPath + File.separator + fileName);
        productVo.setProductThumbImg(File.separator + "imgUpload" + ymdPath + File.separator + "s" + File.separator + "s_" + fileName);

        if (productVo == null){
            return 0;
        }else {

            HashMap<String, String> product = new HashMap<>();

            if (productVo.getProductContents1().length() > 199 && productVo.getProductContents2().length() > 199 && productVo.getProductContents3().length() > 199) {
                return 0;
            }else {
                product.put("productName", productVo.getProductName());
                product.put("productContents1", productVo.getProductContents1());
                product.put("productContents2", productVo.getProductContents2());
                product.put("productContents3", productVo.getProductContents3());
                product.put("productImg", productVo.getProductImg());
                product.put("productThumbImg", productVo.getProductThumbImg());
            }

            return productService.productCreate(product);
        }
    }

    @PutMapping("/product")
    public String productUpdate(){

        return null;
    }

    @DeleteMapping("/product")
    public int productDelete(@RequestBody ProductVo productVo) throws Exception{

        if (productVo == null){
            return 0;
        }else {
            return productService.productDelete(productVo.getProductSeq());
        }
    }

    @GetMapping("/product/{productSeq}")
    public ProductVo productSelectOne(@PathVariable("productSeq") int productSeq) throws Exception{

        //조회 수 증가
        productService.updateView(productSeq);

        return productService.productView(productSeq);
    }

}
