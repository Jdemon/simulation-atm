package com.excite.holidays.atm.configuration;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import com.excite.holidays.atm.model.AtmData;
import com.excite.holidays.atm.model.TransactionsHist;

@Configuration
@Order(1)
@Profile("test")
public class DataConfiguation {
	
	@Bean
	public Map<String,TransactionsHist> transactionsHistMap() {
		return new LinkedHashMap<>();
	}
	
	@Bean
	public AtmData atmData() {
		AtmData atmData = new AtmData();
		return atmData;
		
	}

}
