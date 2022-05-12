package com.shopping.kshopping.product.service;

import com.shopping.kshopping.board.vo.ReplyVo;
import com.shopping.kshopping.product.mapper.ProductMapper;
import com.shopping.kshopping.product.vo.ProductReplyVo;
import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@AllArgsConstructor
@Transactional
@Service("productService")
public class ProductService {

    ProductMapper productMapper;

    public ProductVo productView(int productSeq) throws Exception{

        return productMapper.productView(productSeq);
    }

    public int productCount() throws Exception{

        return productMapper.productCount();
    }

    public int productMinCount() throws Exception{

        return productMapper.productMinCount();
    }

    public int productCreate(HashMap<String, String> product) throws Exception{

        return productMapper.productCreate(product);
    }

    public int productDelete(int productSeq) throws Exception{

        return productMapper.productDelete(productSeq);
    }

    public int productUpdate(ProductVo productVo) throws Exception{

        return productMapper.productUpdate(productVo);
    }

    public void updateView(int productSeq) throws Exception{
        productMapper.updateView(productSeq);
    }

    public ProductReplyVo productReply(int boardSeq, int replySeq) throws Exception{

        ProductReplyVo productReplyVo = new ProductReplyVo();

        productReplyVo.setProductSeq(boardSeq);
        productReplyVo.setReplySeq(replySeq);

        return productMapper.productReply(productReplyVo);
    }

    public int productReplyCount(int productSeq) throws Exception{

        return productMapper.productReplyCount(productSeq);
    }

    public int productReplyMinCount(int productSeq) throws Exception{

        return productMapper.productReplyMinCount(productSeq);
    }

    public int replyCreate(ProductReplyVo productReplyVo) throws Exception {

        return productMapper.replyCreate(productReplyVo);
    }

    public int replyDelete(int replySeq) throws Exception{

        return productMapper.replyDelete(replySeq);
    }

    public ProductVo productSearch(String searchItem, int rowNum) throws Exception{
        ProductVo productVo = new ProductVo();
        productVo.setSearchItem(searchItem);
        productVo.setRowNum(rowNum);

        return productMapper.productSearch(productVo);
    }

    public int searchCount(String searchItem, String productCategory) throws Exception{
        ProductVo productVo = new ProductVo();
        productVo.setSearchItem(searchItem);
        productVo.setProductCategory(productCategory);

        return productMapper.searchCount(productVo);
    }

    public ProductVo categorySearch(String productCategory, int rowNum) throws Exception{
        ProductVo productVo = new ProductVo();
        productVo.setProductCategory(productCategory);
        productVo.setRowNum(rowNum);

        return productMapper.categorySearch(productVo);
    }

}
