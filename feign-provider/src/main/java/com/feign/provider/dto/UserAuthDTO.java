package com.feign.provider.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * feign 请求传参对象
 */
@Data
@NoArgsConstructor
public class UserAuthDTO {
    private String account;
    private String password;

    public UserAuthDTO(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
