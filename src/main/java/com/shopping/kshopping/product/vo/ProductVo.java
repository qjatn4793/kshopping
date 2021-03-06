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

    private int productSeq;
    private String productName;
    private int productLikes;
    private int productViews;
    private String productCategory;
    private String productContents1;
    private String productContents2;
    private String productContents3;
    private String regDate;
    private String modDate;

    private String productImg;
    private String productThumbImg;

    private String searchItem;
    private int rowNum;
}
