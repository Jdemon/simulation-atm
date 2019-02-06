package com.excite.holidays.atm.model;

public class BankNote {
	
	private Integer bankNoteValue;
	private Integer bankNoteAmount;
	
	
	
	public BankNote(Integer bankNoteValue, Integer bankNoteAmount) {
		super();
		this.bankNoteValue = bankNoteValue;
		this.bankNoteAmount = bankNoteAmount;
	}
	public Integer getBankNoteValue() {
		return bankNoteValue;
	}
	public void setBankNoteValue(Integer bankNoteValue) {
		this.bankNoteValue = bankNoteValue;
	}
	public Integer getBankNoteAmount() {
		return bankNoteAmount;
	}
	public void setBankNoteAmount(Integer bankNoteAmount) {
		this.bankNoteAmount = bankNoteAmount;
	}
	@Override
	public String toString() {
		return "BankNote [bankNoteValue=" + bankNoteValue + ", bankNoteAmount=" + bankNoteAmount + "]";
	}
	
	
	
	

}
