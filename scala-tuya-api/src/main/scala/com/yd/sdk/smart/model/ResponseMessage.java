package com.yd.sdk.smart.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import com.alibaba.fastjson.JSONObject;

public class ResponseMessage {

    /** 返回结果成功与否 */
	private boolean				isSuccess;

	/** 错误代码 */
	private String				errorCode;

	/** 错误消息 */
	private String				errorMsg;

    /** 响应json消息体 */
	private Object              result;


    public void setSuccess(boolean isSuccess) {
        this.isSuccess=isSuccess;
    }
    public boolean isSuccess() {
        return this.isSuccess;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode=errorCode;
    }
    public String getErrorCode() {
        return this.errorCode;
    }


    public void setErrorMsg(String errorMsg) {
        this.errorMsg=errorMsg;
    }
    public String getErrorMsg() {
        return this.errorMsg;
    }


    public void setResult(Object result) {
        this.result=result;
    }
    public Object getResult() {
        return this.result;
    }

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
