package com.shopping.kshopping.board.controller;

import com.shopping.kshopping.board.service.BoardService;
import com.shopping.kshopping.board.vo.BoardVo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@AllArgsConstructor
@Controller
public class BoardViewController {

    BoardService boardService;

    @GetMapping("/mainBoard")
    public String mainBoard(){

        return "board/board.html";
    }

    @GetMapping("/detailBoard/{boardSeq}")
    public String boardDetails(@PathVariable("boardSeq") int boardSeq) throws Exception{

        return "board/detailBoard.html";
    }

    @GetMapping("/createBoard")
    public String createBoard() throws Exception{

        return "board/createBoard.html";
    }

}
