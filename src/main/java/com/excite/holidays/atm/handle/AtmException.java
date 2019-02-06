package com.excite.holidays.atm.handle;

public class AtmException extends Exception{

	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String errorMsg;
	
	public AtmException(Integer status,String errorMsg) {
		this.status = status;
		this.errorMsg = errorMsg;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getStatus() {
		return status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	
	

}
