package com.itcourse.mdcc.exception;

import lombok.Data;

//全局异常
@Data
public class GlobalException extends RuntimeException{

    //错误码
    private String code;

    /**--------------------------------------------------------
     传一个错误信息给异常对象
     --------------------------------------------------------**/
    public GlobalException(String message){
        super(message);
    }
    /**--------------------------------------------------------
     传一个错误码的枚举给异常对象
     --------------------------------------------------------**/
    public GlobalException(GlobalExceptionEnum globalExceptionEnum){
        super(globalExceptionEnum.getMessage());
        this.code = globalExceptionEnum.getCode();
    }
}