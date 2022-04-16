package com.coconut.ds20.dto.input.job;

import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/4/11 21:40
 * File: XxlJobInputDto
 * Project: dS20
 */

@Data
public class XxlJobInputDto {
    private String name;
    private String cron;
    private String callbackUrl;
}
