package com.excite.holidays.atm.model;

import java.time.LocalDateTime;
import java.util.List;

public class TransactionsHist {
	
	private Integer txnSeq;
	private LocalDateTime txnDateTime;
	private Integer withdrawAmount;
	private List<BankNote> withdrawBankNotes;
	private Integer atmBalance;
	
	public Integer getTxnSeq() {
		return txnSeq;
	}
	public void setTxnSeq(Integer txnSeq) {
		this.txnSeq = txnSeq;
	}
	public LocalDateTime getTxnDateTime() {
		return txnDateTime;
	}
	public void setTxnDateTime(LocalDateTime txnDateTime) {
		this.txnDateTime = txnDateTime;
	}
	public Integer getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(Integer withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public Integer getAtmBalance() {
		return atmBalance;
	}
	public void setAtmBalance(Integer atmBalance) {
		this.atmBalance = atmBalance;
	}
	public List<BankNote> getWithdrawBankNotes() {
		return withdrawBankNotes;
	}
	public void setWithdrawBankNotes(List<BankNote> withdrawBankNotes) {
		this.withdrawBankNotes = withdrawBankNotes;
	}
	@Override
	public String toString() {
		return "TransactionsHist [txnSeq=" + txnSeq + ", txnDateTime=" + txnDateTime + ", withdrawAmount="
				+ withdrawAmount + ", withdrawBankNotes=" + withdrawBankNotes + ", atmBalance=" + atmBalance + "]";
	}
	
	
	
}
