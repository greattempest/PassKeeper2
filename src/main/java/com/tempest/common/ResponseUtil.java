package com.tempest.common;

import com.tempest.util.Response;
import com.tempest.util.ResponseCodeEnum;

public class ResponseUtil {

    public  static Response success(Object object){
        Response response=new Response();
        response.setCode(ResponseCodeEnum.SUCCESS.getCode());
        response.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
        response.setContent(object);
        return response;
    }

    public static Response success(){
        return  success(null);
    }

    public static Response error(Object object){
        Response response=new Response();
        response.setCode(ResponseCodeEnum.SYSTEM_ERROR.getCode());
        response.setMsg(ResponseCodeEnum.SYSTEM_ERROR.getMsg());
        response.setContent(object);
        return response;
    }

    public static Response error(Integer code,String msg,Object object){
        Response response=new Response();
        response.setCode(code);
        response.setMsg(msg);
        response.setContent(object);
        return response;
    }

    public static Response error(ResponseCodeEnum codeEnum, Object object){
        Response response=new Response();
        response.setCode(codeEnum.getCode());
        response.setMsg(codeEnum.getMsg());
        response.setContent(object);
        return response;
    }

    public static Response error(){
        return  error(null);
    }

}
