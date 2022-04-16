package com.coconut.ds20.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds20.dto.common.ResponseData;
import com.coconut.ds20.dto.input.account.LoginInputDto;
import com.coconut.ds20.dto.input.user.ResetPasswordInputDto;
import com.coconut.ds20.dto.input.user.UserCreateInputDto;
import com.coconut.ds20.dto.input.user.UserUpdateInputDto;
import com.coconut.ds20.dto.output.account.LoginOutputDto;
import com.coconut.ds20.dto.output.user.UserOutputDto;
import com.coconut.ds20.entity.UserEntity;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 分页查询
     *
     * @param pageIndex
     * @param pageSize
     * @param username
     * @param name
     * @return
     */
    ResponseData<List<UserOutputDto>> query(Integer pageIndex, Integer pageSize, String username, String name);

    // 查询所有
    ResponseData<List<UserOutputDto>> queryAll();

    // 根据id获取
    ResponseData<UserOutputDto> getById(Integer id);

    // 创建
    ResponseData<UserOutputDto> create(UserCreateInputDto inputDto);

    // 修改
    ResponseData<UserOutputDto> update(UserUpdateInputDto inputDto);

    // 删除
    ResponseData<Boolean> delete(Integer id);

    // 重置密码
    ResponseData<Boolean> resetPassword(ResetPasswordInputDto inputDto);



}
