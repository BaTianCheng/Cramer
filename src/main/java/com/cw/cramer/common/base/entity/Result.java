package com.cw.cramer.common.base.entity;

/**
 * 结果类
 * @author wicks
 */
public class Result {
	
	/**
	 * 结果码
	 */
	private String resultCode;
	
	/**
	 * 结果描述
	 */
	private String message;
	
	/**
	 * 结果数据
	 */
	private Object data;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
