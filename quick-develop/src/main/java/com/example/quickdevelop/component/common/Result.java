package com.example.quickdevelop.component.common;

import lombok.Data;

@Data
public class Result {
    private  int code;
    private  String msg;
    private  Object data;

    public Result(){
    }
    public Result(String msg){
        this.msg = msg;
    }
    public Result(int code, String msg){
        this.code = code;
        this.msg = msg;
    }
    public Result(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(String msg){
        return new Result(200,msg);
    }

    public static Result success(String msg,Object obj){
        return new Result(200,msg,obj);
    }

    public static Result error(String msg){
        return new Result(500,msg);
    }

}
