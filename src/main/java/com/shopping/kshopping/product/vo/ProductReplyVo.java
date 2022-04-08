package com.shopping.kshopping.product.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductReplyVo {

    private int productSeq;
    private int replySeq;
    private String replyWriter;
    private String replyContents;
}
