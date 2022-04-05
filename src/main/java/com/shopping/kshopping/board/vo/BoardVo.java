package com.shopping.kshopping.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardVo {

    private int boardSeq;
    private String boardName;
    private String boardTitle;
    private String boardWriter;
    private int boardLikes;
    private int boardViews;
    private String boardContents;
    private String boardReply;
    private String regDate;
    private String modDate;
}
