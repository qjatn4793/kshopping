package com.shopping.kshopping.board.mapper;

import com.shopping.kshopping.board.vo.BoardVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BoardMapper {

    int loginCheck(BoardVo loginVo);
    
}