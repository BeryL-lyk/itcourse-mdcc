package com.itcourse.mdcc.result;

import lombok.Builder;
import lombok.Data;

//返回JSON结果
@Data
//建造者模式
//@Builder
public class JSONResult {

    private boolean success = true;

    private String message = "成功";

    //错误码，用来描述错误类型 ，1000 表示么有错误
    private String code = "1000";

    //返回的数据
    private Object data;

    /** 创建当前实例 **/
    public static JSONResult success(){
        return new JSONResult();
    }
    /** 创建当前实例 **/
    public static JSONResult success(Object obj){
        JSONResult instance = new JSONResult();
        instance.setData(obj);
        return instance;
    }

    public static JSONResult success(Object obj,String code){
        JSONResult instance = new JSONResult();
        instance.setCode(code);
        instance.setData(obj);
        return instance;
    }
    /** 创建当前实例 **/

    public static JSONResult error(String message,String code){
        JSONResult instance = new JSONResult();
        instance.setMessage(message);
        instance.setSuccess(false);
        instance.setCode(code);
        return instance;
    }

    public static JSONResult error(){
        JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(false);
        return jsonResult;
    }

    /** 创建当前实例 **/
    public static JSONResult error(String message){
        return error(message,null);
    }

}
