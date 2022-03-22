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

    public int loginCheck(BoardVo loginVo){

        return boardMapper.loginCheck(loginVo);
    }

}
