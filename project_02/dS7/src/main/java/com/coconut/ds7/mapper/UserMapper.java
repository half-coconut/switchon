package com.coconut.ds7.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.coconut.ds7.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {

}
