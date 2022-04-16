package com.coconut.ds20.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.account.LoginInputDto;
import com.coconut.ds20.dto.input.user.ResetPasswordInputDto;
import com.coconut.ds20.dto.input.user.UserCreateInputDto;
import com.coconut.ds20.dto.input.user.UserUpdateInputDto;
import com.coconut.ds20.dto.output.account.LoginOutputDto;
import com.coconut.ds20.dto.output.user.UserOutputDto;
import com.coconut.ds20.entity.UserEntity;
import com.coconut.ds20.enums.StatusEnum;
import com.coconut.ds20.mapper.UserMapper;
import com.coconut.ds20.service.UserService;
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
 * Date: 2022/3/6 20:11
 * File: UserServiceImpl
 * Project: dS20
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    SessionUtil sessionUtil;

    @Override
    public ResponseData<List<UserOutputDto>> query(Integer pageIndex, Integer pageSize, String username, String name) {
        ResponseData<List<UserOutputDto>> responseData;
        try {
            // 查询数据
            LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            if (username != null) {
                lambdaQueryWrapper.like(UserEntity::getUsername, username);
            }
            if (name != null) {
                lambdaQueryWrapper.eq(UserEntity::getName, name);
            }
            lambdaQueryWrapper.eq(UserEntity::getStatus, StatusEnum.ENABLE.getStatus());
            IPage<UserEntity> page = new Page<>();
            page.setSize(pageSize);
            page.setCurrent(pageIndex);
            page = this.page(page, lambdaQueryWrapper);
            // entity 转DTO
            List<UserOutputDto> outputDtos = page.getRecords().stream().map(s -> modelMapper.map(s, UserOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, page.getTotal());

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }

        return responseData;
    }

    @Override
    public ResponseData<List<UserOutputDto>> queryAll() {
        ResponseData<List<UserOutputDto>> responseData;
        try {
            // 查询数据
            List<UserEntity> userEntities = this.list();

            // entity 转DTO, 只返回所有启用的数据
            List<UserOutputDto> outputDtos = userEntities.stream().filter(s -> s.getStatus() == StatusEnum.ENABLE.getStatus()).map(s -> modelMapper.map(s, UserOutputDto.class)).collect(Collectors.toList());
            responseData = ResponseData.success(outputDtos, (long) outputDtos.size());

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }

        return responseData;
    }

    @Override
    public ResponseData<UserOutputDto> getById(Integer id) {
        ResponseData<UserOutputDto> responseData;
        try {
            // 查询数据
            UserEntity userEntity = super.getById(id);

            // entity 转DTO
            UserOutputDto outputDtos = modelMapper.map(userEntity, UserOutputDto.class);
            responseData = ResponseData.success(outputDtos);

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }

        return responseData;
    }

    @Override
    public ResponseData<UserOutputDto> create(UserCreateInputDto inputDto) {
        ResponseData<UserOutputDto> responseData;
        try {
            // 数据验证，规则验证和业务验证
            List<String> errorMessages = new ArrayList<>();
            // 用户名不能同名
            LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserEntity::getUsername, inputDto.getUsername());
            lambdaQueryWrapper.eq(UserEntity::getStatus, StatusEnum.ENABLE.getStatus());
            UserEntity existUserEntity = this.getOne(lambdaQueryWrapper, false);
            if (existUserEntity != null) {
                errorMessages.add(String.format("用户名[%s]已经存在。", inputDto.getUsername()));
            }
            if (!CollectionUtils.isEmpty(errorMessages)) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(errorMessages.stream().collect(Collectors.joining(",")));
                return responseData;
            }

            // DTO 转 entity
            UserEntity userEntity = modelMapper.map(inputDto, UserEntity.class);
            // 密码加密
            String password = PasswordUtil.encrypt(inputDto.getPassword(), inputDto.getUsername());
            userEntity.setPassword(password);
            userEntity.setStatus(StatusEnum.ENABLE.getStatus());
            // 保存
            this.save(userEntity);
            UserOutputDto outputDto = modelMapper.map(userEntity, UserOutputDto.class);
            responseData = ResponseData.success(outputDto);

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }

        return responseData;
    }

    @Override
    public ResponseData<UserOutputDto> update(UserUpdateInputDto inputDto) {
        ResponseData<UserOutputDto> responseData;
        try {
            // 数据验证，规则验证和业务验证
            List<String> errorMessages = new ArrayList<>();
            // id不存在时，需要提示
            UserEntity isExistIDuserEntity = super.getById(inputDto.getId());
            if (isExistIDuserEntity == null) {
                errorMessages.add(String.format("用户ID[%S]不存在", inputDto.getId()));
            }
            // 用户名不能同名
            LambdaQueryWrapper<UserEntity> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(UserEntity::getUsername, inputDto.getUsername());
            // 当id不等于 已存在的id时候，校验用户名是否存在
            lambdaQueryWrapper.ne(UserEntity::getId, inputDto.getId());
            lambdaQueryWrapper.eq(UserEntity::getStatus, StatusEnum.ENABLE.getStatus());
            UserEntity existUserEntity = this.getOne(lambdaQueryWrapper, false);
            if (existUserEntity != null) {
                errorMessages.add(String.format("用户名[%s]已经存在。", inputDto.getUsername()));
            }

            if (!CollectionUtils.isEmpty(errorMessages)) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(errorMessages.stream().collect(Collectors.joining(",")));
                return responseData;
            }

            // DTO 转 entity
            UserEntity userEntity = modelMapper.map(inputDto, UserEntity.class);
            // 注意：密码不更新，需要使用旧的密码
            UserEntity oldUserEntity = super.getById(inputDto.getId());
            userEntity.setPassword(oldUserEntity.getPassword());
            // 处理
            this.updateById(userEntity);
            UserOutputDto outputDto = modelMapper.map(userEntity, UserOutputDto.class);
            responseData = ResponseData.success(outputDto);

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }

        return responseData;
    }

    @Override
    public ResponseData<Boolean> delete(Integer id) {
        ResponseData<Boolean> responseData;
        try {
            // 查询数据
            UserEntity userEntity = super.getById(id);
            // 逻辑删除
            userEntity.setStatus(StatusEnum.DELETE.getStatus());
            //处理
            this.updateById(userEntity);

            responseData = ResponseData.success(true);

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }

        return responseData;
    }

    @Override
    public ResponseData<Boolean> resetPassword(ResetPasswordInputDto inputDto) {
        ResponseData<Boolean> responseData;
        try {
            // 数据验证，规则验证和业务验证
            List<String> errorMessages = new ArrayList<>();
            // 判断id是否存在
            UserEntity userEntity = super.getById(inputDto.getId());
            if (userEntity == null) {
                errorMessages.add("用户不存在。");
            }
            if (!CollectionUtils.isEmpty(errorMessages)) {
                responseData = new ResponseData<>();
                responseData.setCode(1);
                responseData.setMessage(errorMessages.stream().collect(Collectors.joining(",")));
                return responseData;
            }

            // 重置密码
            String newPassword = PasswordUtil.encrypt(inputDto.getPassword(), userEntity.getUsername());
            userEntity.setPassword(newPassword);
            // 处理
            this.updateById(userEntity);
            responseData = ResponseData.success(true);

        } catch (Exception ex) {
            responseData = ResponseData.failure(ex.toString());
            ex.printStackTrace();
        }

        return responseData;
    }


}
