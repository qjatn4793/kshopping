package com.shopping.kshopping.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductVo {

    public int productSeq;
    private String productName;
    private int productLikes;
    private int productViews;
    private int productStatus;
    private String regDate;
    private String modDate;

}
