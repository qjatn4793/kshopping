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

        if (boardVo.getBoardContents() == null && boardVo.getBoardContents().isEmpty() && boardVo.getBoardTitle() == null && boardVo.getBoardTitle().isEmpty()) { // 글 내용, 제목이 없으면
            return 0;
        }else { // 글 내용이 있으면
            return boardService.boardCreate(boardVo);
        }

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

        HashMap<String, ReplyVo> boardReply = new HashMap<>();

        int boardReplyCount = boardService.boardReplyCount(boardSeq);

        if (boardReplyCount != 0) {
            for (int i=1; boardReplyCount >= i; i++) {
                if(boardService.boardReply(boardSeq, i) != null){
                    boardReply.put(valueOf(i), boardService.boardReply(boardSeq, i));
                }
            }
        }
        return boardReply;
    }

    @PostMapping("/boardReply/{boardSeq}")
    public int replyCreate(@PathVariable("boardSeq") int boardSeq, @RequestBody ReplyVo replyVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);

        if (replyVo.getReplyWriter().equals(userId)){ //넘겨온 댓글 작성자와 세션 댓글 작성자가 같을 때
            if (replyVo.getReplyContents() == null && replyVo.getReplyContents().isEmpty()) { //댓글 내용이 없으면
                return 0;
            }else { //댓글 내용이 있으면
                replyVo.setBoardSeq(boardSeq);
                return boardService.replyCreate(replyVo);
            }

        }else {
            return 0;
        }
    }

    @DeleteMapping("/boardReply/{boardSeq}")
    public int replyDelete(@PathVariable("boardSeq") int boardSeq, @RequestBody ReplyVo replyVo, HttpServletRequest request) throws Exception{

        HttpSession session = request.getSession();
        Object O_userId = session.getAttribute("userId");
        String userId = valueOf(O_userId);

        boardService.boardReply(boardSeq, replyVo.getReplySeq()).getReplyWriter();

        if ((boardService.boardReply(boardSeq, replyVo.getReplySeq()).getReplyWriter()).equals(userId)) {
            return boardService.replyDelete(replyVo.getReplySeq());
        }else {
            return 0;
        }
    }

}
