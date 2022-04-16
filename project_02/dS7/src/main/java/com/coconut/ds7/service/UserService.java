package com.coconut.ds7.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds7.dto.common.RequestData;
import com.coconut.ds7.dto.common.ResponseData;
import com.coconut.ds7.dto.input.user.UserCreateInputDto;
import com.coconut.ds7.dto.input.user.UserQueryInputDto;
import com.coconut.ds7.dto.input.user.UserUpdateInputDto;
import com.coconut.ds7.dto.output.user.UserOutputDto;
import com.coconut.ds7.entity.UserEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@CacheConfig(cacheNames = "user")
public interface UserService extends IService<UserEntity> {
    ResponseData<List<UserOutputDto>> query(RequestData<UserQueryInputDto> requestData);

    //    @Cacheable(key = "#root.methodName")
    ResponseData<List<UserOutputDto>> queryAll();

    //    @Cacheable(key = "#p0")
    ResponseData<UserOutputDto> getById(Integer id);

    //    @CacheEvict(allEntries = true)
    ResponseData<UserOutputDto> create(UserCreateInputDto inputDto);

    //    @CachePut(key = "#p0.id")
//    @CacheEvict(allEntries = true)
    ResponseData<UserOutputDto> update(UserUpdateInputDto inputDto);

    //    @CacheEvict(allEntries = true)
    ResponseData<Boolean> delete(Integer id);

    ResponseData<UserOutputDto> getByUsername(String username);

}
