package com.shopping.kshopping.product.controller;

import com.shopping.kshopping.board.vo.ReplyVo;
import com.shopping.kshopping.main.controller.MainController;
import com.shopping.kshopping.main.vo.MainVo;
import com.shopping.kshopping.product.service.ProductService;
import com.shopping.kshopping.product.vo.ProductReplyVo;
import com.shopping.kshopping.product.vo.ProductVo;
import com.shopping.kshopping.util.UploadFileUtils;
import lombok.AllArgsConstructor;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;

@AllArgsConstructor
@RestController
@ResponseBody
public class ProductController {

    ProductService productService;

    @GetMapping("/productView")
    public HashMap<String, ProductVo> productView() throws Exception{
        HashMap<String, ProductVo> productView = new HashMap<>();

        int productMaxCount = productService.productCount();
        int productMinCount = productService.productMinCount();

        for (int i = productMinCount; productMaxCount >= i; i++) {
            if (productService.productView(i) != null) {
                productView.put(valueOf(i), productService.productView(i));
            }
        }
        return productView;
    }

    @PostMapping("/productView")
    public HashMap<String, ProductVo> productSearchView(@RequestBody ProductVo productVo) throws Exception{
        HashMap<String, ProductVo> productView = new HashMap<>();

        String searchItem = productVo.getSearchItem();
        String productCategory = productVo.getProductCategory();

        int searchCount = productService.searchCount(searchItem, productCategory);

        final String checkString = "[a-zA-Z0-9???-???]"; // ???????????? ??????
        Matcher matchTest;
        Matcher matchTest2;
        /*if (searchItem != "" || searchItem != null) {
            matchTest = Pattern.compile(checkString).matcher(searchItem); // ???????????? ???????????? ????????? true
        }else {
            matchTest.find() = true;
        }*/
        matchTest2 = Pattern.compile(checkString).matcher(productCategory); // ???????????? ?????? ???????????? ????????? true

        if (searchCount > 0) {
            if (matchTest2.find() == true) {
                for (int i = 0; searchCount > i; i++) {
                    if(searchItem == null) { // ?????? ???????????? ?????? ??????
                        if (productService.categorySearch(productCategory, 0) != null) { // ??????????????? ?????? ??????
                            if (productCategory.equals("??????")) {
                                productView.put(valueOf(i), productService.categorySearch(productCategory, i));
                            } else if (productCategory.equals("??????")) {
                                if (productService.categorySearch(productCategory, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.categorySearch(productCategory, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.categorySearch(productCategory, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.categorySearch(productCategory, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.categorySearch(productCategory, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.categorySearch(productCategory, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.categorySearch(productCategory, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.categorySearch(productCategory, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.categorySearch(productCategory, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.categorySearch(productCategory, i));
                                }
                            } else {
                                productView.put(valueOf(i), productService.categorySearch(productCategory, i));
                            }
                        } else {

                            //System.out.println("???????????? ??????");
                        }
                    }else {
                        if (productService.productSearch(searchItem, 0) != null) { // ?????? ???????????? ?????? ??????
                            if (productCategory.equals("??????")) {
                                productView.put(valueOf(i), productService.productSearch(searchItem, i));
                            } else if (productCategory.equals("??????")) {
                                if (productService.productSearch(searchItem, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.productSearch(searchItem, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.productSearch(searchItem, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.productSearch(searchItem, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.productSearch(searchItem, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.productSearch(searchItem, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.productSearch(searchItem, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.productSearch(searchItem, i));
                                }
                            } else if (productCategory.equals("??????")) {
                                if (productService.productSearch(searchItem, i).getProductCategory().equals("??????")) {
                                    productView.put(valueOf(i), productService.productSearch(searchItem, i));
                                }
                            } else {
                                productView.put(valueOf(i), productService.productSearch(searchItem, i));
                            }
                        }else {
                            productView.put(valueOf(i), productService.productSearch(searchItem, i));
                        }
                    }
                }
                return productView;
            } else {
                //???????????? ???????????? ????????? ??????
                return null;
            }
        }else {
            ProductVo clearVo = new ProductVo();
            productView.put("0", clearVo);

            return productView;
        }
    }

    @GetMapping("/product/{productSeq}")
    public ProductVo productSelectOne(@PathVariable("productSeq") int productSeq) throws Exception{

        //?????? ??? ??????
        productService.updateView(productSeq);

        return productService.productView(productSeq);
    }

    @GetMapping("/productReply/{productSeq}")
    public HashMap<String, ProductReplyVo> boardReply(@PathVariable("productSeq") int productSeq) throws Exception{

        HashMap<String, ProductReplyVo> boardReply = new HashMap<>();

        int productReplyCount = productService.productReplyCount(productSeq);
        int productReplyMinCount = productService.productReplyMinCount(productSeq);

        if(productReplyCount != 0) {
            for (int i = productReplyMinCount; productReplyCount >= i; i++) {
                if (productService.productReply(productSeq, i) != null) {
                    boardReply.put(valueOf(i), productService.productReply(productSeq, i));
                }
            }
        }

        return boardReply;
    }

    @PostMapping("/productReply/{productSeq}")
    public int replyCreate(@PathVariable("productSeq") int productSeq, @RequestBody ProductReplyVo productReplyVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);

        try {
            if (productReplyVo.getReplyWriter().equals(userId)) { //????????? ?????? ???????????? ?????? ?????? ???????????? ?????? ???
                if (productReplyVo.getReplyContents() == null && productReplyVo.getReplyContents().isEmpty()) { //?????? ????????? ?????????
                    return 0;
                } else { //?????? ????????? ?????????
                    productReplyVo.setProductSeq(productSeq);
                    return productService.replyCreate(productReplyVo);
                }
            } else {
                return 0;
            }
        }catch (NullPointerException e) {
            return 2;
        }
    }

    @DeleteMapping("/productReply/{productSeq}")
    public int replyDelete(@PathVariable("productSeq") int productSeq, @RequestBody ProductReplyVo productReplyVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);

        if ((productService.productReply(productSeq, productReplyVo.getReplySeq()).getReplyWriter()).equals(userId)) { // ?????? ???????????? ?????? ????????? ???????????? ???????????? ?????? ??????
            return productService.replyDelete(productReplyVo.getReplySeq());

        }else {
            return 0;
        }
    }

}
