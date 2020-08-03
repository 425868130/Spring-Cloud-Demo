package com.example.authcenter.service.account;

import com.example.authcenter.service.account.ao.AccountCreateForm;
import com.example.authcenter.service.account.base.AccountInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AccountInfoServiceTest {
    @Autowired
    private AccountInfoService accountInfoService;

    @Test
    void addAccount() {
        AccountCreateForm accountCreateForm = new AccountCreateForm();
        accountCreateForm.setAccount("xjw19960613");
        accountCreateForm.setEmail("425868130@qq.com");
        accountCreateForm.setPassword("");
        accountInfoService.createAccount(accountCreateForm);
    }
}
