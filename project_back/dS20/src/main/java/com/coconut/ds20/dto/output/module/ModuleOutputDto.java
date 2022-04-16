package com.coconut.ds20.dto.output.module;

import com.coconut.ds20.dto.output.BaseOutputDto;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/5 22:58
 * File: ModuleOutputDto
 * Project: dS20
 */

@Data
public class ModuleOutputDto extends BaseOutputDto {
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Integer projectId;
}
