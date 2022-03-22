package com.shopping.kshopping.login.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo {
    private String userId;
    private String userPw;
    private int status;
    
}
