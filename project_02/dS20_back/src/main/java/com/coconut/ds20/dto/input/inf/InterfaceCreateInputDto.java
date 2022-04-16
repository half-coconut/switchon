package com.coconut.ds20.dto.input.inf;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/5 22:35
 * File: InterfaceCreateInputDto
 * Project: dS20
 */

@Data
public class InterfaceCreateInputDto {
    @NotNull(message = "接口名称不能为空")
    private String name;
    @NotNull(message = "接口路径不能为空")
    @Length(min = 1,max = 200,message = "接口路径长度必须在1-200之间")
    private String path;
    @NotNull(message = "请求方法不能为空")
    private String requestMethod;
    @NotNull(message = "响应类型不能为空")
    private String responseType;
    private String description;
    private Integer developerId;
    private String developerName;
    @NotNull(message = "所属项目ID不能为空")
    private Integer projectId;
    @NotNull(message = "所属模块ID不能为空")
    private Integer moduleId;
}
