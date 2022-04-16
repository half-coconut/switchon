package com.coconut.ds7.dto.common;

import com.coconut.ds7.dto.common.page.Paging;
import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/9 21:21
 * File: RequestData
 * Project: dS7
 */

/**
 * 请求对象
 *
 * @param <T>
 */
@Data
public class RequestData<T> implements Serializable {
    private Paging page;
    private T conditions;
    private String orderBy;
}
