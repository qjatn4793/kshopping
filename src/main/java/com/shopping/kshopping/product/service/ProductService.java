package com.shopping.kshopping.product.service;

import com.shopping.kshopping.product.mapper.ProductMapper;
import com.shopping.kshopping.product.vo.ProductVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public int productCreate(String productName) throws Exception{

        return productMapper.productCreate(productName);
    }

    public int productDelete(int productSeq) throws Exception{

        return productMapper.productDelete(productSeq);
    }

}
