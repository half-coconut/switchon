package com.coconut.ds20.dto.output.environment;

import com.coconut.ds20.dto.output.BaseOutputDto;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/7 0:48
 * File: EnvironmentOutputDto
 * Project: dS20
 */

@Data
public class EnvironmentOutputDto extends BaseOutputDto {
    private Integer id;
    private String name;
    private String host;
    private String dbConfig;
    private String description;
    private Integer status;
    private Integer projectId;
}
