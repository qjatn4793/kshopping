package com.shopping.kshopping.board.controller;

import com.shopping.kshopping.board.service.BoardService;
import com.shopping.kshopping.board.vo.BoardVo;
import com.shopping.kshopping.board.vo.ReplyVo;
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

    @DeleteMapping("/board/{boardSeq}")
    public int boardDelete(@PathVariable("boardSeq") int boardSeq, @RequestBody BoardVo boardVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);

        if (boardVo.getBoardWriter().equals(userId)) {
            return boardService.boardDelete(boardSeq);
        }else {
            return 0;
        }
    }

    @GetMapping("/board/{boardSeq}")
    public BoardVo boardSelectOne(@PathVariable("boardSeq") int boardSeq) throws Exception{

        //조회 수 증가
        boardService.updateView(boardSeq);

        return boardService.boardView(boardSeq);
    }

    @GetMapping("/boardReply/{boardSeq}")
    public HashMap<String, ReplyVo> boardReply(@PathVariable("boardSeq") int boardSeq) throws Exception{

        int boardReplyCount = boardService.boardReplyCount(boardSeq);
        HashMap<String, ReplyVo> boardReply = new HashMap<>();

        for (int i=1; boardReplyCount >= i; i++) {
            if(boardService.boardReply(i) != null){
                boardReply.put(valueOf(i), boardService.boardReply(i));
            }
        }

        return boardReply;
    }

    @PostMapping("/boardReply/{boardSeq}")
    public int replyCreate(@PathVariable("boardSeq") int boardSeq, @RequestBody ReplyVo replyVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);

        System.out.println(replyVo.getReplyWriter());
        System.out.println(userId);

        if (replyVo.getReplyWriter().equals(userId)){
            replyVo.setBoardSeq(boardSeq);

            return boardService.replyCreate(replyVo);
        }else {
            return 0;
        }
    }

    @DeleteMapping("/boardReply/{boardSeq}")
    public int replyDelete(@PathVariable("boardSeq") int replySeq, @RequestBody BoardVo boardVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);

        if (boardVo.getBoardWriter().equals(userId)) {
            //return boardService.replyDelete(replySeq);
            return 0;

        }else {
            return 0;
        }
    }

}
