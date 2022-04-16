package com.coconut.ds20.dto.input.task;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 17:03
 * File: ChangeIsJobOrNotInputDto
 * Project: dS20
 */

@Data
public class ChangeIsJobOrNotInputDto {
    @NotNull(message = "测试任务ID不能为空")
    private Integer taskId;
    @NotNull(message = "是否定时执行不能为空")
    private Boolean isJob;
}
