package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.account.LoginInputDto;
import com.coconut.ds20.dto.output.account.LoginOutputDto;
import com.coconut.ds20.entity.UserEntity;

public interface AccountService extends IService<UserEntity> {

    // 登录
    ResponseData<LoginOutputDto> login(LoginInputDto inputDto);
}
