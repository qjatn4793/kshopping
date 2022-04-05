package com.shopping.kshopping.board.controller;

import com.shopping.kshopping.board.service.BoardService;
import com.shopping.kshopping.board.vo.BoardVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static java.lang.String.valueOf;

@AllArgsConstructor
@RestController
@ResponseBody
public class BoardController {
    BoardService boardService;

    @GetMapping("/board")
    public HashMap<String, BoardVo> boardView() throws Exception{

        HashMap<String, BoardVo> boardView = new HashMap<>();

        int boardCount = boardService.boardCount();

        for (int i=1; boardCount >= i; i++) {

            if(boardService.boardView(i) != null){
                boardView.put(valueOf(i), boardService.boardView(i));
            }
        }

        return boardView;
    }

    @GetMapping("/board/{boardSeq}")
    public BoardVo productSelectOne(@PathVariable("boardSeq") int boardSeq) throws Exception{

        //조회 수 증가
        boardService.updateView(boardSeq);

        return boardService.boardView(boardSeq);
    }

}
