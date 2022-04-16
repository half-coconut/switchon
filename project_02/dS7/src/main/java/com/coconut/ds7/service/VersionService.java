package com.coconut.ds7.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.coconut.ds7.dto.common.RequestData;
import com.coconut.ds7.dto.common.ResponseData;
import com.coconut.ds7.dto.input.version.VersionCreateInputDto;
import com.coconut.ds7.dto.input.version.VersionQueryInputDto;
import com.coconut.ds7.dto.input.version.VersionUpdateInputDto;
import com.coconut.ds7.dto.output.version.VersionOutputDto;
import com.coconut.ds7.entity.VersionEntity;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 版本服务
 */
@CacheConfig(cacheNames = "version")
public interface VersionService extends IService<VersionEntity> {
    ResponseData<List<VersionOutputDto>> query(RequestData<VersionQueryInputDto> requestData);

    @Cacheable(key = "#root.methodName")
    ResponseData<List<VersionOutputDto>> queryAll();

    ResponseData<VersionOutputDto> getById(Integer id);

    @CacheEvict(allEntries = true)
    ResponseData<VersionOutputDto> create(VersionCreateInputDto versionCreateInputDto);

    @CacheEvict(allEntries = true)
    ResponseData<VersionOutputDto> update(VersionUpdateInputDto versionUpdateInputDto);

    @CacheEvict(allEntries = true)
    ResponseData<Boolean> delete(Integer id);
}
