package com.itcourse.mdcc.exception;


import com.itcourse.mdcc.result.JSONResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**--------------------------------------------------------
 全局异常处理

 @RestControllerAdvice ：贴在类上，这个类就可以在controller的方法执行前，或者执行后做一些事情
 --------------------------------------------------------**/
//@ControllerAdvice
@RestControllerAdvice
public class GlobalExceptionHandler {

    //拦截器其他异常
    @ExceptionHandler(Exception.class)
    public JSONResult exception(Exception e){
        e.printStackTrace();

        return JSONResult.error("系统错误");
    }

    //拦截异常 : 这个注解就可以拦截器 GlobleException 异常
    @ExceptionHandler(GlobalException.class)
    public JSONResult globalException(GlobalException e){
        e.printStackTrace();
        return JSONResult.error(e.getMessage(),e.getCode());

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONResult methodArgumentNotValidException(MethodArgumentNotValidException e){
        e.printStackTrace();
        StringBuilder sb = new StringBuilder();
        e.getBindingResult().getAllErrors().forEach(error->{
            sb.append(error.getDefaultMessage()).append(";");
        });
        return JSONResult.error(sb.toString(),GlobalExceptionEnum.PARAM_VALIDATE_EXCEPTION.getCode());

    }

}
