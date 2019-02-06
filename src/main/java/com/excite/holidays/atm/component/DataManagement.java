package com.excite.holidays.atm.component;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.excite.holidays.atm.model.AtmData;
import com.excite.holidays.atm.model.TransactionsHist;

@Component
@Order(2)
public class DataManagement {
	
	private Integer txnSeq=0;
	
	@Autowired
	private Map<String,TransactionsHist> transactionsHistMap;
	
	@Autowired
	private AtmData atmData;
	
	public Integer generateNextTxnSeq() {
		return ++txnSeq;
	}

	public void addTxnHist(TransactionsHist transactionsHist){
		
		if(transactionsHist.getTxnSeq() == null || transactionsHist.getTxnSeq() == 0) {
			transactionsHist.setTxnSeq(generateNextTxnSeq());
		}
		
		transactionsHistMap.put(transactionsHist.getTxnSeq().toString(), transactionsHist);
	}
	
	public AtmData getAtmData() {
		return atmData;
	}

	public Integer getAtmBalance() {
		
		Integer balance = 0;
		
		Map<String, Integer> bankData = atmData.getBankNotes();
		
		for(Entry<String, Integer> entry : bankData.entrySet()) {
			balance = balance + (Integer.parseInt(entry.getKey())*entry.getValue());
		}
		
		return balance;
		
	}
	
	

}
