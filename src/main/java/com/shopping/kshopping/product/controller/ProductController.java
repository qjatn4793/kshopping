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

        for (int i=productMinCount; productMaxCount >= i; i++) {
            if(productService.productView(i) != null){
                productView.put(valueOf(i), productService.productView(i));
            }
        }

        return productView;
    }

    @GetMapping("/product/{productSeq}")
    public ProductVo productSelectOne(@PathVariable("productSeq") int productSeq) throws Exception{

        //조회 수 증가
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
            if (productReplyVo.getReplyWriter().equals(userId)) { //넘겨온 댓글 작성자와 세션 댓글 작성자가 같을 때
                if (productReplyVo.getReplyContents() == null && productReplyVo.getReplyContents().isEmpty()) { //댓글 내용이 없으면
                    return 0;
                } else { //댓글 내용이 있으면
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

        if ((productService.productReply(productSeq, productReplyVo.getReplySeq()).getReplyWriter()).equals(userId)) { // 댓글 작성자와 삭제 시도한 사용자가 같을경우 아래 실행
            return productService.replyDelete(productReplyVo.getReplySeq());

        }else {
            return 0;
        }
    }

}
