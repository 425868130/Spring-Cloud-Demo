package com.example.authcenter.service.account.ao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 账号创建信息表单
 *
 * @author xujw
 * @since 2020-08-03 14:08:07
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class AccountCreateForm {
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String nickName;
    @Email(message = "邮箱格式不正确")
    private String email;
    private String telephone;
    //短信验证码
    private String smsVerifyCode;

    @AssertTrue
    public boolean isValid() {
        return true;
    }
}
