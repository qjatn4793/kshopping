package com.shopping.kshopping.product.mapper;

import com.shopping.kshopping.board.vo.ReplyVo;
import com.shopping.kshopping.product.vo.ProductReplyVo;
import com.shopping.kshopping.product.vo.ProductVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Mapper
public interface ProductMapper {

    ProductVo productView(int productSeq);

    int productCount();

    int productMinCount();

    int productCreate(HashMap<String, String> product);

    int productDelete(int productSeq);

    int productUpdate(ProductVo productVo);

    int updateView(int productSeq);

    ProductReplyVo productReply(ProductReplyVo productReplyVo);

    int productReplyCount(int productSeq);

    int productReplyMinCount(int productSeq);

    int replyCreate(ProductReplyVo productReplyVo);

    int replyDelete(int replySeq);
}
