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
    private String productContents1;
    private String productContents2;
    private String productContents3;
    private String regDate;
    private String modDate;

    private String productImg;
    private String productThumbImg;
}
