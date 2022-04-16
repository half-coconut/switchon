package com.coconut.ds20.dto.output.inf;

import com.coconut.ds20.dto.output.BaseOutputDto;
import com.coconut.ds20.dto.output.module.ModuleOutputDto;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/6 22:41
 * File: InterfaceQueryOutputDto
 * Project: dS20
 */

@Data
public class InterfaceQueryOutputDto extends BaseOutputDto {
    private Integer id;
    private String name;
    private String path;
    private String requestMethod;
    private String responseType;
    private String description;
    private Integer status;
    private Integer developerId;
    private String developerName;
    private Integer projectId;
    private Integer moduleId;
    private ModuleOutputDto module;
}
