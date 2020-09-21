/**
 * 
 */
package com.derushan.common;

import org.springframework.http.ResponseEntity;

/**
 * @author Derushan Sep 21, 2020
 */
public class CommonResponseBuilder {
	private String msg;
	private String status;
	private Integer statusCode;
	private Object error;
	private Object data;

	public CommonResponseBuilder msg(String msg) {
		this.msg = msg;
		return this;
	}

	public CommonResponseBuilder status(String status) {
		this.status = status;
		return this;
	}

	public CommonResponseBuilder statusCode(Integer statusCode) {
		this.statusCode = statusCode;
		return this;
	}

	public CommonResponseBuilder error(Object error) {
		this.error = error;
		return this;
	}

	public CommonResponseBuilder data(Object data) {
		this.data = data;
		return this;
	}

	public CommonResponse build() {
		CommonResponse response = new CommonResponse();
		response.setMsg(msg);
		response.setStatus(status);
		response.setStatusCode(statusCode);
		response.setData(data);
		response.setError(error);
		return response;
	}

	public ResponseEntity<Object> entity() {
		return ResponseEntity.status(statusCode).body(build());
	}

	public String json() {
		return build().toJson();
	}
}
