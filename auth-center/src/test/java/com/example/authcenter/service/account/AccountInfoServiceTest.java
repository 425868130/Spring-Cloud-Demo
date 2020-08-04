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
        accountCreateForm.setAccount("13254@sss123");
        accountCreateForm.setEmail("425868130@qq.com");
        accountCreateForm.setPassword("5151445664684");
        accountInfoService.create(accountCreateForm);
    }

    @Test
    void deleteAccount() {
        accountInfoService.delete(-1);
    }

    @Test
    void getByAccountName() {
        accountInfoService.getByAccountName("");
    }
}
