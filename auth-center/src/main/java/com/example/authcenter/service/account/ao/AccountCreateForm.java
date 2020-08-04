package com.example.authcenter.service.account.ao;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
    @Pattern(regexp = "^(?![0-9-_]+$)[0-9A-Za-z@-_]{6,12}$", message = "账号格式不正确")
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotBlank(message = "密码不能为空")
    private String password;
    private String nickName;
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;
    private String telephone;
    //短信验证码
    private String smsVerifyCode;

    @AssertTrue(message = "账号参数有误,请检查")
    public boolean isValid() {
        return true;
    }
}
