package com.shopping.kshopping.controller.test;

import com.shopping.kshopping.model.SearchVO;
import org.springframework.web.bind.annotation.*;

@RestController // 여기는 컨트롤러라고 알려주는 @RestController 어노테이션 사용
@RequestMapping("/api") // 여기로 들어올 path를 지정할 @RequestMapping 어노테이션 사용 localhost:8080/api
public class GetAPIController {

    @RequestMapping(method = RequestMethod.GET, path = "/getRequest")   // 일반적인 get방식 호출 : localhost:8080/api/getRequest
    public String getRequest(){
        return "this is getRequest";
    }

    @GetMapping("/getParameters")  // 파라메터 사용 : http://localhost:8080/api/getParameters?id=qjatn4792&email=qjatn4792@gmail.com
    public String getParameters(@RequestParam String id, @RequestParam String email){
        return "아이디는 "+id+" 이메일은 "+email;
    }

    @GetMapping("/getMultiParameters") // VO 사용 : http://localhost:8080/api/getMultiParameters?id=qjatn4792&email=qjatn4792@gmail.com
    public String getMultiParameters(SearchVO searchVo) {
        return "VO사용 아이디는 "+searchVo.getId()+" 이메일은 "+searchVo.getEmail();
    }

    @GetMapping("/getMultiParametersRtnJson") // Json타입 출력 : http://localhost:8080/api/getMultiParametersRtnJson?id=qjatn4792&email=qjatn4792@gmail.com
    public SearchVO getMultiParametersRtnJson(SearchVO searchVo) {
        return searchVo;
    }

}
