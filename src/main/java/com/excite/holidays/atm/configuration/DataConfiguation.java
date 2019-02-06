package com.excite.holidays.atm.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import com.excite.holidays.atm.model.AtmData;
import com.excite.holidays.atm.model.TransactionsHist;

@Configuration
@Order(1)
@Profile("dev")
public class DataConfiguation {
	
	@Autowired
	private Environment env;
	
	@Bean
	public Map<String,TransactionsHist> transactionsHistMap() {
		return new LinkedHashMap<>();
	}
	
	@Bean
	public AtmData atmData() {
		AtmData atmData = new AtmData();
		Map<String,Integer> bankNotes = new LinkedHashMap<>();
		
		bankNotes.put("20",Integer.valueOf(env.getProperty("bank.note.twenty")));
		bankNotes.put("50",Integer.valueOf(env.getProperty("bank.note.fifty")));
		bankNotes.put("100",Integer.valueOf(env.getProperty("bank.note.one-hundred")));
		bankNotes.put("500",Integer.valueOf(env.getProperty("bank.note.five-hundred")));
		bankNotes.put("1000",Integer.valueOf(env.getProperty("bank.note.onet-housand")));
		
		atmData.setBankNotes(bankNotes);
		
		return atmData;
		
	}

}
