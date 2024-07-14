package com.itcourse.mdcc.utils;

import com.itcourse.mdcc.exception.GlobalException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AssertUtil {

    //手机的正则表达式
    private static final Pattern CHINA_PATTERN_PHONE = Pattern.compile("^((13[0-9])|(14[0,1,4-9])|(15[0-3,5-9])|(16[2,5,6,7])|(17[0-8])|(18[0-9])|(19[0-3,5-9]))\\d{8}$");


    /**--------------------------------------------------------
    手机号断言
    --------------------------------------------------------**/
    public static void isPhone(String phone,String message){
        Matcher m = CHINA_PATTERN_PHONE.matcher(phone);
        if(!m.matches()){
            throw new GlobalException(message);
        }
    }


    /**--------------------------------------------------------
    断言 不为空，如果为空，抛异常
    --------------------------------------------------------**/
    public static void isNotEmpty(String text, String message) {
        if (text == null || text.trim().length() == 0) {
            throw new GlobalException(message);
        }
    }


    /**--------------------------------------------------------
    断言对象为空
    --------------------------------------------------------**/
    public static void isNull(Object obj , String message){
        if(obj != null){
            throw new GlobalException(message);
        }
    }
    public static void isNotNull(Object obj , String message){
        if(obj == null){
            throw new GlobalException(message);
        }
    }

    /**--------------------------------------------------------
     断言false,如果为true,我报错
     --------------------------------------------------------**/
    public static void isFalse(boolean isFalse , String message){
        if(isFalse){
            throw new GlobalException(message);
        }
    }
    public static void isTrue(boolean isTrue , String message){
        if(!isTrue){
            throw new GlobalException(message);
        }
    }


    /**--------------------------------------------------------
     断言两个字符串一致
     --------------------------------------------------------**/
    public static void isEquals(String s1,String s2 , String message){
        isNotEmpty(s1, "不可为空");
        isNotEmpty(s2, "不可为空");
        if(!s1.equals(s2)){
            throw new GlobalException(message);
        }
    }
    public static void isEqualsTrim(String s1,String s2 , String message){
        isNotEmpty(s1, "不可为空");
        isNotEmpty(s2, "不可为空");
        if(!s1.trim().equals(s2.trim())){
            throw new GlobalException(message);
        }
    }

    public static void isEqualsIgnoreCase(String s1,String s2 , String message){
        isNotEmpty(s1, "不可为空");
        isNotEmpty(s2, "不可为空");
        if(!s1.trim().equalsIgnoreCase(s2.trim())){
            throw new GlobalException(message);
        }
    }

}
