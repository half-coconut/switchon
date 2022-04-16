package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.account.LoginInputDto;
import com.coconut.ds20.dto.output.account.LoginOutputDto;
import com.coconut.ds20.entity.UserEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.UserMapper;
import com.coconut.ds20.service.AccountService;
import com.coconut.ds20.util.PasswordUtil;
import com.coconut.ds20.util.SessionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 1:35
 * File: AccountServiceImpl
 * Project: dS20
 */

@Service
public class AccountServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements AccountService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SessionUtil sessionUtil;

    @Override
    public ResponseData<LoginOutputDto> login(LoginInputDto inputDto) {
        ResponseData<LoginOutputDto> responseData;
        try {
            // 数据验证，规则验证和业务验证
            List<String> errorMessages = new ArrayList<>();
            // 判断id是否存在
            LambdaQueryWrapper<UserEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(UserEntity::getUsername, inputDto.getUsername());
            queryWrapper.eq(UserEntity::getStatus, StatusEnum.ENABLE.getStatus());
            UserEntity userEntity = this.getOne(queryWrapper, false);
            if (userEntity != null) {
                // 用户名存在，验证密码
                String password = PasswordUtil.encrypt(inputDto.getPassword(), inputDto.getUsername());
                if (userEntity.getPassword().equalsIgnoreCase(password) == false) {
                    errorMessages.add("密码不正确。");
                }
            } else {
                errorMessages.add(String.format("用户名[%s]不存在。", inputDto.getUsername()));
            }

            if (!CollectionUtils.isEmpty(errorMessages)) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(errorMessages.stream().collect(Collectors.joining(",")));
                return responseData;
            }
            // 记录一下最后一次登录时间
            userEntity.setLastLoginTime(new Date());
            // 登录信息要保存到会话
            SessionUtil.CurrentUser currentUser = new SessionUtil.CurrentUser();
            currentUser.setUserEntity(userEntity);
            sessionUtil.setCurrentUser(currentUser);
            // TODO 设置其他的信息


            // 处理
            this.updateById(userEntity);
            LoginOutputDto outputDto = modelMapper.map(userEntity, LoginOutputDto.class);
            responseData = ResponseData.success(outputDto);

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }
        return responseData;
    }
}
