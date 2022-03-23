package com.shopping.kshopping.product.mapper;

import com.shopping.kshopping.product.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProductMapper {

    ProductVo productView(int productSeq);

    int productCount();
}
