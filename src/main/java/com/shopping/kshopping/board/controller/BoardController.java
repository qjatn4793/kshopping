package com.shopping.kshopping.board.controller;

import com.shopping.kshopping.board.service.BoardService;
import com.shopping.kshopping.board.vo.BoardVo;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    @PostMapping("/board")
    public int boardCreate(@RequestBody BoardVo boardVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);
        boardVo.setBoardWriter(userId);

        return boardService.boardCreate(boardVo);
    }

    @GetMapping("/board/{boardSeq}")
    public BoardVo productSelectOne(@PathVariable("boardSeq") int boardSeq) throws Exception{

        //조회 수 증가
        boardService.updateView(boardSeq);

        return boardService.boardView(boardSeq);
    }

}
