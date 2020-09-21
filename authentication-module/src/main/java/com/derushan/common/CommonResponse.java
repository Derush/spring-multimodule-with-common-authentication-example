/**
 * 
 */
package com.derushan.common;

import java.util.StringJoiner;


/**
 * @author Derushan
 * Sep 21, 2020
 */
public class CommonResponse {
	private String msg;
	private String status;
	private Integer statusCode;
	private Object error;
	private Object data;
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the error
	 */
	public Object getError() {
		return error;
	}
	/**
	 * @param error the error to set
	 */
	public void setError(Object error) {
		this.error = error;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	
	
	/**
	 * @return the statusCode
	 */
	public Integer getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public static CommonResponseBuilder builder() {
		return new CommonResponseBuilder();
	}

	public String toJson() {
		return new StringJoiner(", ", "{", "}")
				.add("\"statusCode\": " + statusCode)
				.add("\"msg\": \"" + msg + "\"")
				.add("\"status\": \"" + status + "\"")
				.add("\"error\": " + error)
				.add("\"data\": " + data)
				.toString();
	}
}
