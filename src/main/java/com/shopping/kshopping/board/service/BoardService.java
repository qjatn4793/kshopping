package com.shopping.kshopping.board.service;

import com.shopping.kshopping.board.mapper.BoardMapper;
import com.shopping.kshopping.board.vo.BoardVo;
import com.shopping.kshopping.board.vo.ReplyVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@AllArgsConstructor
@Transactional
@Service("boardService")
public class BoardService {

    BoardMapper boardMapper;

    /*public int loginCheck(BoardVo loginVo){

        return boardMapper.loginCheck(loginVo);
    }*/

    public BoardVo boardView(int boardSeq) throws Exception{

        return boardMapper.boardView(boardSeq);
    }

    public int boardCount() throws Exception{

        return boardMapper.boardCount();
    }

    public void updateView(int boardSeq) throws Exception{
        boardMapper.updateView(boardSeq);
    }

    public int boardCreate(BoardVo boardVo) throws Exception {

        return boardMapper.boardCreate(boardVo);
    }

    public int boardDelete(int boardSeq) throws Exception{

        return boardMapper.boardDelete(boardSeq);
    }

    public ReplyVo boardReply(int boardSeq, int replySeq) throws Exception{

        ReplyVo replyVo = new ReplyVo();

        replyVo.setBoardSeq(boardSeq);
        replyVo.setReplySeq(replySeq);

        return boardMapper.boardReply(replyVo);
    }

    public int boardReplyCount(int boardSeq) throws Exception{

        return boardMapper.boardReplyCount(boardSeq);
    }

    public int replyCreate(ReplyVo replyVo) throws Exception {

        return boardMapper.replyCreate(replyVo);
    }

    public int replyDelete(int replySeq) throws Exception{

        return boardMapper.replyDelete(replySeq);
    }





}
