package com.excite.holidays.atm.service.model;

import java.util.List;

import com.excite.holidays.atm.model.BankNote;

public class WithdrawData {
	
	private Integer withdrawAmount;
	private List<BankNote> withdrawBankNotes;
	public Integer getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Integer withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public List<BankNote> getWithdrawBankNotes() {
		return withdrawBankNotes;
	}
	public void setWithdrawBankNotes(List<BankNote> withdrawBankNotes) {
		this.withdrawBankNotes = withdrawBankNotes;
	}
	
	

}
