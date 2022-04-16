package com.coconut.ds20.dto.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Author: coconut
 * Description: TODO
 * Date: 2022/3/6 20:36
 * File: ResponseData
 * Project: dS20
 */

/**
 * 通用返回包装
 */
@Data
public class ResponseData<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;
    private Long total;

    public static final int SUCCESS_CODE=0;
    public static final int FAILURE_CODE=999;

    public static<T> ResponseData success(){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(SUCCESS_CODE);
        responseData.setMessage("操作成功。");
        return responseData;
    }
    public static<T> ResponseData success(String message){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(SUCCESS_CODE);
        responseData.setMessage(message);
        return responseData;
    }
    public static<T> ResponseData success(Integer code){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(code);
        responseData.setMessage("操作成功。");
        return responseData;
    }
    public static<T> ResponseData success(Integer code,String message){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(code);
        responseData.setMessage(message);
        return responseData;
    }
    public static<T> ResponseData success(T data){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(SUCCESS_CODE);
        responseData.setData(data);
        responseData.setMessage("操作成功。");
        return responseData;
    }
    public static<T> ResponseData success(T data,Long total){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(SUCCESS_CODE);
        responseData.setData(data);
        responseData.setTotal(total);
        responseData.setMessage("操作成功。");
        return responseData;
    }

    public static<T> ResponseData failure(){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(FAILURE_CODE);
        responseData.setMessage("操作失败。");
        return responseData;
    }
    public static<T> ResponseData failure(String message){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(FAILURE_CODE);
        responseData.setMessage(message);
        return responseData;
    }
    public static<T> ResponseData failure(Integer code){
        ResponseData<T> responseData =new ResponseData<>();
        responseData.setCode(code);
        responseData.setMessage("操作失败。");
        return responseData;
    }
}
