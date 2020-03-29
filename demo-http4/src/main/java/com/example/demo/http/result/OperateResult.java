package com.example.demo.http.result;

import java.io.Serializable;

public class OperateResult<T> implements Serializable {
    public static final int CODE_SUCCESS = 0;
    public static final int CODE_FAILURE = -1;
    private static final long serialVersionUID = 1L;
    private Integer returncode;
    private String message;
    private String bizMessage;
    private T result;

    public OperateResult() {
    }

    public String getBizMessage() {
        return this.bizMessage;
    }

    public void setBizMessage(String bizMessage) {
        this.bizMessage = bizMessage;
    }

    public Integer getReturncode() {
        return this.returncode;
    }

    public void setReturncode(Integer returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return this.returncode != null && this.returncode.equals(0);
    }

    public void setSuccess() {
        this.returncode = 0;
    }

    public void successBody(T result) {
        this.returncode = 0;
        this.message = "ok";
        this.result = result;
    }

    public static OperateResult fail(String message, String bizMessage) {
        OperateResult jr = new OperateResult();
        jr.returncode = -1;
        jr.message = message;
        jr.bizMessage = bizMessage;
        return jr;
    }

    public static OperateResult failMsg(String message) {
        OperateResult jr = new OperateResult();
        jr.returncode = -1;
        jr.message = message;
        return jr;
    }

    public static OperateResult failBizMsg(String message) {
        OperateResult jr = new OperateResult();
        jr.returncode = -1;
        jr.bizMessage = message;
        return jr;
    }

    public static <T> OperateResult succ(T result) {
        OperateResult operateResult = succ();
        operateResult.setResult(result);
        return operateResult;
    }

    public static <T> OperateResult succ(T result, String msg) {
        OperateResult operateResult = succ();
        operateResult.setResult(result);
        operateResult.setMessage(msg);
        return operateResult;
    }

    public static <T> OperateResult succ() {
        OperateResult jr = new OperateResult();
        jr.returncode = 0;
        return jr;
    }
}
