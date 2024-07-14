package com.itcourse.mdcc.utils;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**--------------------------------------------------------
Http工具类

<dependency>
    <groupId>commons-codec</groupId>
    <artifactId>commons-codec</artifactId>
    <version>1.4</version>
</dependency>

<dependency>
    <groupId>commons-httpclient</groupId>
    <artifactId>commons-httpclient</artifactId>
    <version>3.1</version>
</dependency>
--------------------------------------------------------**/
public class HttpUtil {

    /** 发送post请求
     * map.put("Uid" ,"xxx");
     * map.put("Key" ,"xxxxx");
     * **/
    public static String sendPost(String url,Map<String,String> param){

        //结果
        String result = null;

        try {
            //创建http客户端
            HttpClient client = new HttpClient();
            //创建Post请求
            PostMethod post = new PostMethod(url);
            //设置请求头
            //在头文件中设置转码
            post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");

            //封装参数
            if(param != null && !param.isEmpty()){
                Set<String> keySet = param.keySet();

                //取出所有的key
                Iterator<String> iterator = keySet.iterator();

                //用来封装参数的数组
                NameValuePair[] data = new NameValuePair[keySet.size()];

                int index = 0 ;

                while (iterator.hasNext()){
                    String key = iterator.next();
                    String value = param.get(key);
                    data[index++] = new NameValuePair(key, value);
                }
                //把数据设置到请求体
                post.setRequestBody(data);
            }

            //执行发送
            client.executeMethod(post);

            //得到响应结果
            result = new String(post.getResponseBodyAsString().getBytes("utf-8"));

            post.releaseConnection();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("网络请求失败["+e.getMessage()+"]");
        }

        return result;
    }

}
