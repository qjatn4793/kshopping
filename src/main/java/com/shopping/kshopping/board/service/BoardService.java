package com.shopping.kshopping.board.service;

import com.shopping.kshopping.board.mapper.BoardMapper;
import com.shopping.kshopping.board.vo.BoardVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        System.out.println(boardVo + "service VO");

        boardMapper.boardCreate(boardVo);

        return 1;
    }

}
