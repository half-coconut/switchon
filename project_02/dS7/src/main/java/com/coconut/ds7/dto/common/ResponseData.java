package com.coconut.ds7.dto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/1/9 21:22
 * File: ResponseData
 * Project: dS7
 */

/**
 * 响应包装对象
 */
@Data
public class ResponseData<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    private Long total;

    public static final int SUCCESS_CODE = 0;
    public static final int FAILURE_CODE = 888;

    public static ResponseData success() {
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage("操作成功！");

        return responseData;
    }

    public static ResponseData success(String massage) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setMessage(massage);

        return responseData;
    }

    public static <T> ResponseData success(T data) {
        ResponseData<T> responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setData(data);
        responseData.setMessage("操作成功！");

        return responseData;
    }

    public static <T> ResponseData success(String message, T data) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(0);
        responseData.setMessage(message);
        responseData.setData(data);

        return responseData;
    }

    public static <T> ResponseData success(T data, Long total) {
        ResponseData<T> responseData = new ResponseData<>();
        responseData.setCode(0);
        responseData.setMessage("操作成功。");
        responseData.setData(data);
        responseData.setTotal(total);

        return responseData;
    }

    public static <T> ResponseData success(String message, T data, Long total) {
        ResponseData<T> responseData = new ResponseData<T>();
        responseData.setCode(0);
        responseData.setMessage(message);
        responseData.setData(data);
        responseData.setTotal(total);

        return responseData;
    }

    public static ResponseData failure() {
        ResponseData responseData = new ResponseData();
        responseData.setCode(888);
        responseData.setMessage("操作失败！");

        return responseData;
    }

    public static ResponseData failure(String errormessage) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(888);
        responseData.setMessage(errormessage);

        return responseData;
    }
}
