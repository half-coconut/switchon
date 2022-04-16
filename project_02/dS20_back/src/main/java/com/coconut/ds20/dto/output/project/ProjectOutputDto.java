package com.coconut.ds20.dto.output.project;

import com.coconut.ds20.dto.output.BaseOutputDto;
import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/4 23:10
 * File: ProjectOutputDto
 * Project: dS20
 */

@Data
public class ProjectOutputDto extends BaseOutputDto {
    private Integer id;
    private String name;
    private String description;
    private Integer status;
    private Integer ownerId;
    private String ownerName;
}
