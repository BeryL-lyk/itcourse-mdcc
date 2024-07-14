package com.itcourse.mdcc.exception;

public enum GlobalExceptionEnum {

    // 公共
    OK("1000","成功"),
    ERROR("1001","系统内部异常"),

    PARAM_IS_NULL_EXCEPTION("2000","参数不能为空"),
    PARAM_VALIDATE_EXCEPTION("2001","参数校验异常");

    //错误码
    private String code;
    //错误信息
    private String message;

    GlobalExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public String getMessage() {
        return message;
    }

}
