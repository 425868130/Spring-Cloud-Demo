package com.example.authcenter.service.account.mapper;

import com.example.authcenter.entity.AccountInfo;
import com.example.authcenter.service.account.ao.AccountCreateForm;
import com.example.authcenter.service.account.ao.AccountUpdateForm;
import com.example.authcenter.service.account.bo.AccountInfoBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccountInfoBo toAccountBo(AccountInfo accountInfo);

    AccountInfo formToAccountInfo(AccountCreateForm form);

    AccountInfo formToAccountInfo(AccountUpdateForm form);
}
