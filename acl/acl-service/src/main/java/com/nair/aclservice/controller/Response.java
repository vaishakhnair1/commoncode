package com.nair.aclservice.controller;

public class Response<T> {

	private ApiCallStatus status = ApiCallStatus.FAIL;
	
	private T response;
	
	public enum ApiCallStatus{
		SUCCESS, FAIL;
	}

	public ApiCallStatus getStatus() {
		return status;
	}

	public void setStatus(ApiCallStatus status) {
		this.status = status;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}
	
}
