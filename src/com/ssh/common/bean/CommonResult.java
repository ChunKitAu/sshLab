package com.ssh.common.bean;

/**
 * @auther ChunKitAu
 * @create 2020-05-16 16
 */
public class CommonResult {


    private Integer code;

    private String message;

    private Object data;

    private final static String SUCCESSMESSAGE = "请求成功！";
    private final static String FAILMESSAGE = "请求失败！";
    private final static String VALIDATMESSAGE = "表单验证失败！";
    private final static String AUTHMESSAGE = "身份认证失败！";

    //成功
    private final static Integer SUCCESSCODE = 200;
    //失败
    private final static Integer FAILCODE = 500;
    //表单验证失败
    private final static Integer VALIDATECODE = 510;
    //身份认证失败
    private final static Integer AUTHCODE = 520;


    public static CommonResult success() {
        return new CommonResult(SUCCESSCODE, SUCCESSMESSAGE, null);
    }

    public static CommonResult success(Object data) {
        return new CommonResult(SUCCESSCODE, SUCCESSMESSAGE, data);
    }

    public static CommonResult fail() {
        return new CommonResult(FAILCODE, FAILMESSAGE, null);
    }

    public static CommonResult validateFail(Object data) {
        return new CommonResult(VALIDATECODE, VALIDATMESSAGE, data);
    }

    public static CommonResult authFail() {
        return new CommonResult(AUTHCODE, AUTHMESSAGE, null);
    }

    public CommonResult() {
    }

    public CommonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
