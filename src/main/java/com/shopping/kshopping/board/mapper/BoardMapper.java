package com.shopping.kshopping.board.mapper;

import com.shopping.kshopping.board.vo.BoardVo;
import com.shopping.kshopping.board.vo.ReplyVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {

    //int loginCheck(BoardVo loginVo);

    BoardVo boardView(int boardSeq);

    int boardCount();

    int boardMinCount();

    int updateView(int boardSeq);

    int boardCreate(BoardVo boardVo);

    int boardDelete(int boardSeq);

    ReplyVo boardReply(ReplyVo replyVo);

    int boardReplyCount(int boardSeq);

    int boardReplyMinCount(int boardSeq);

    int replyCreate(ReplyVo replyVo);

    int replyDelete(int replySeq);
    
}
