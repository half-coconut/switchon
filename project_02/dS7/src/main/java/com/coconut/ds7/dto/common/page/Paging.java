package com.coconut.ds7.dto.common.page;

import lombok.Data;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/9 21:30
 * File: Paging
 * Project: dS7
 */

/**
 * 分页类
 */
@Data
public class Paging {
    /**
     * 当前页号
     */
    private Long pgaeIndex;
    /**
     * 每页大小
     */
    private Long pgaeSize;

    public Paging() {

    }

    public Paging(Long pgaeIndex, Long pgaeSize) {
        this.pgaeIndex = pgaeIndex;
        this.pgaeSize = pgaeSize;
    }
}
