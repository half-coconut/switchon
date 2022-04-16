package com.coconut.ds7.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.coconut.ds7.dto.common.RequestData;
import com.coconut.ds7.dto.common.ResponseData;
import com.coconut.ds7.dto.common.page.Paging;
import com.coconut.ds7.dto.input.user.UserCreateInputDto;
import com.coconut.ds7.dto.input.user.UserQueryInputDto;
import com.coconut.ds7.dto.input.user.UserUpdateInputDto;
import com.coconut.ds7.dto.output.user.UserOutputDto;
import com.coconut.ds7.entity.UserEntity;
import com.coconut.ds7.mapper.UserMapper;
import com.coconut.ds7.service.UserService;
import com.coconut.ds7.util.RedistTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/7 21:04
 * File: UserServiceImpl
 * Project: dS7
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    // 缓存名字
    private final String USER_NAME = "user";

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedistTemplateUtil redistTemplateUtil;

    @Override
    public ResponseData<List<UserOutputDto>> query(RequestData<UserQueryInputDto> requestData) {
        ResponseData<List<UserOutputDto>> responseData = new ResponseData<>();

        try {
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            // 查询条件
            UserQueryInputDto condition = requestData.getConditions();
            if (condition.getName() != null) {
                queryWrapper.like("name", condition.getName());
            }
            if (condition.getUsername() != null) {
                queryWrapper.like("username", condition.getUsername());
            }
            // 如果没有传分页，则获取所有数据
            if (requestData.getPage() == null)
                requestData.setPage(new Paging(1L, Long.MAX_VALUE));

            // 获取数据
            IPage<UserEntity> queryPage = new Page<>(requestData.getPage().getPgaeIndex(), requestData.getPage().getPgaeSize());
            queryPage = this.page(queryPage, queryWrapper);
            List<UserOutputDto> outputDtos = new ArrayList<>();
            queryPage.getRecords().forEach(item -> {
                UserOutputDto outputDto = new UserOutputDto();
                BeanUtils.copyProperties(item, outputDto);
                outputDtos.add(outputDto);
            });
            responseData.setData(outputDtos);
            responseData.setTotal(queryPage.getTotal());
            responseData.setCode(ResponseData.SUCCESS_CODE);
            responseData.setMessage("查询成功！");

        } catch (Exception ex) {
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<List<UserOutputDto>> queryAll() {
        // 1、从缓存取，如果有
        // 2、从数据库取
        // 3、将数据放入缓存
        ResponseData<List<UserOutputDto>> responseData;
        try {

            List<UserOutputDto> userOutputDtos;
            // 如果有redis缓存，取缓存
            if (redisTemplate.hasKey(USER_NAME) == true) {
//                userOutputDtos = (List<UserOutputDto>) redisTemplate.opsForValue().get(USER_ALL_KEY);
                userOutputDtos = redisTemplate.opsForList().range(USER_NAME, 0, redisTemplate.opsForList().size(USER_NAME));
            } else {
                // 否则取数据库，并且存入redis
                List<UserEntity> entities = this.list();
                // entities 转dto
                userOutputDtos = entities.stream().map(item -> {
                    return modelMapper.map(item, UserOutputDto.class);
                }).collect(Collectors.toList());
//                redisTemplate.opsForValue().set(USER_ALL_KEY, userOutputDtos);
                redisTemplate.opsForList().leftPushAll(USER_NAME, userOutputDtos);
                // 设置redis 的key的过期时间
//                redisTemplate.expire(USER_ALL_KEY, Duration.ofSeconds(45));
                // 使用redisTemple读取配置文件里设置的过期时间
                redisTemplate.expire(USER_NAME, redistTemplateUtil.getExpire(USER_NAME));
            }
            responseData = ResponseData.success(userOutputDtos);
        } catch (Exception ex) {
            responseData = new ResponseData<>();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<UserOutputDto> getById(Integer id) {
        ResponseData<UserOutputDto> responseData;
        try {
            UserEntity entity = super.getById(id);
            UserOutputDto outputDto = new UserOutputDto();
            BeanUtils.copyProperties(entity, outputDto);

            responseData = ResponseData.success(outputDto);
        } catch (Exception ex) {
            responseData = new ResponseData<>();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<UserOutputDto> create(UserCreateInputDto inputDto) {
        ResponseData<UserOutputDto> responseData;
        try {
            UserEntity entity = new UserEntity();
            entity.setCreateBy(1);
            entity.setUpdateBy(1);
            BeanUtils.copyProperties(inputDto, entity);

            // 使用mybatis保存最新数据
            Boolean success = this.save(entity);

            // 过期缓存 操作
            redisTemplate.delete(USER_NAME);

            // 重新获取最新数据
            responseData = this.getById(entity.getId());

        } catch (Exception ex) {
            responseData = new ResponseData<>();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<UserOutputDto> update(UserUpdateInputDto inputDto) {
        ResponseData<UserOutputDto> responseData;
        try {
            UserEntity entity = new UserEntity();
            BeanUtils.copyProperties(inputDto, entity);
            // 更新数据
            Boolean success = this.updateById(entity);

            // 过期缓存 操作
            redisTemplate.delete(USER_NAME);

            // 重新获取最新数据
            responseData = this.getById(entity.getId());
        } catch (Exception ex) {
            responseData = new ResponseData<>();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }

        return responseData;
    }

    @Override
    public ResponseData<Boolean> delete(Integer id) {
        ResponseData<Boolean> responseData;
        try {
            Boolean success = this.removeById(id);

            // 过期缓存 操作
            redisTemplate.delete(USER_NAME);

            responseData = ResponseData.success(success);
        } catch (Exception ex) {
            responseData = new ResponseData<>();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }

    @Override
    public ResponseData<UserOutputDto> getByUsername(String username) {
        ResponseData<UserOutputDto> responseData;
        try {
            QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", username);
            UserEntity userEntity = this.getOne(queryWrapper);
            // 使用modelMapper转换返回信息的dto
            UserOutputDto userOutputDto = modelMapper.map(userEntity, UserOutputDto.class);

            responseData = ResponseData.success(userOutputDto);
        } catch (Exception ex) {
            responseData = new ResponseData<>();
            responseData.setCode(ResponseData.FAILURE_CODE);
            responseData.setMessage(ex.toString());
        }
        return responseData;
    }


    @Override
    public List<UserEntity> list() {
        return super.list();
    }
}
