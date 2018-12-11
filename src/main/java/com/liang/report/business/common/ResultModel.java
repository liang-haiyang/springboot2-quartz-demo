package com.liang.report.business.common;

/**
 * @author lianghaiyang 2018/11/6 18:34
 */
public final class ResultModel {
    public static final int RETURN_CODE_SUCCESS = 0;
    public static final int RETURN_CODE_FAIL = 1;
    private int code = 0;
    private Object body;

    private ResultModel() {
        this.code = 0;
        this.body = null;
    }

    private ResultModel(int code, Object body) {
        this.code = code;
        this.body = body;
    }

    public int getCode() {
        return this.code;
    }

    public Object getBody() {
        return this.body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public static ResultModel buildModel(boolean flag) {
        return flag ? successModel() : failModel();
    }

    public static ResultModel successModel() {
        return successModel("");
    }

    public static ResultModel successModel(Object body) {
        return new ResultModel(0, body);
    }

    public static ResultModel failModel() {
        return failModel("");
    }

    public static ResultModel failModel(Object body) {
        return new ResultModel(1, body);
    }
}
