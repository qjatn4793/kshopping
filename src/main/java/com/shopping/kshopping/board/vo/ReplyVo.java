package com.shopping.kshopping.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyVo {

    private int boardSeq;
    private int replySeq;
    private String replyWriter;
    private String replyContents;
}
