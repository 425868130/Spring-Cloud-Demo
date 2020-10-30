package com.example.authcenter.controller;

import com.example.authcenter.service.account.base.AccountInfoService;
import com.example.common.define.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountInfoService accountInfoService;

    public AccountController(AccountInfoService accountInfoService) {
        this.accountInfoService = accountInfoService;
    }

    @RequestMapping("/get/{uid}")
    public Result getAccount(@PathVariable("uid") Long uid) {
        return Result.ok(accountInfoService.getByUid(uid));
    }

    public Result create() {
        return null;
    }
}
