package com.funny.combo.camel.consts;

import java.io.Serializable;

/**
 * @param <T>
 * @author fangli
 * @desc 服务返回处理结果
 * @time 19/12/3 下午4:32
 */
public class OperateResult<T> implements Serializable {


    public static final int CODE_SUCCESS = 0;

    public static final int CODE_FAILURE = -1;
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * 返回编码
     */
    private Integer returncode;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 业务对象
     */
    private T result;

    public Integer getReturncode() {
        return returncode;
    }

    public void setReturncode(Integer returncode) {
        this.returncode = returncode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return returncode != null && returncode.equals(CODE_SUCCESS) ? true : false;
    }

    public void setSuccess() {
        this.returncode = CODE_SUCCESS;
    }


    public void successBody(T result) {
        this.returncode = CODE_SUCCESS;
        this.message = "ok";
        this.result = result;
    }

    public static OperateResult fail(String message) {
        OperateResult jr = new OperateResult();
        jr.returncode = CODE_FAILURE;
        jr.message = message;
        return jr;
    }

    public static OperateResult failMsg(String message) {
        return fail(message);
    }


    public static OperateResult failBizMsg(String message) {
        return fail(message);
    }

    public static <T> OperateResult succ(T result) {
        OperateResult operateResult = succ();
        operateResult.setResult(result);
        operateResult.returncode = CODE_SUCCESS;
        return operateResult;
    }

    public static <T> OperateResult succ(T result, String msg) {
        OperateResult operateResult = succ();
        operateResult.setResult(result);
        operateResult.returncode = CODE_SUCCESS;
        operateResult.setMessage(msg);
        return operateResult;
    }

    public static <T> OperateResult succ() {
        OperateResult jr = new OperateResult();
        jr.returncode = CODE_SUCCESS;
        return jr;
    }

    public static <T> OperateResult getInstance(Integer returncode, T result, String msg) {
        OperateResult jr = new OperateResult();
        jr.returncode = returncode;
        jr.message = msg;
        jr.result = result;
        return jr;
    }
}
