package com.excite.holidays.atm.service.model;

public class Response {
	
	private Integer status = 0;
	private String errorMsg = "Success";
	private WithdrawData data;
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public WithdrawData getData() {
		return data;
	}
	public void setData(WithdrawData data) {
		this.data = data;
	}
	
	

}
