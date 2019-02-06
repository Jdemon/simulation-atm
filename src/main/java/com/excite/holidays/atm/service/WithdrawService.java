package com.excite.holidays.atm.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excite.holidays.atm.component.DataManagement;
import com.excite.holidays.atm.model.AtmData;
import com.excite.holidays.atm.model.BankNote;
import com.excite.holidays.atm.model.TransactionsHist;
import com.excite.holidays.atm.service.model.Request;
import com.excite.holidays.atm.service.model.Response;
import com.excite.holidays.atm.service.model.WithdrawData;

@Service
public class WithdrawService {

	@Autowired
	private AtmData atmData;

	@Autowired
	private DataManagement dataManagement;

	public Response withdraw(Request request) {

		Response response = new Response();

		Map<String, Integer> bankDataMaps = atmData.getBankNotes();
		
		Integer[] bankNoteValues = bankDataMaps.keySet().stream().map(Integer::parseInt)
				.sorted(Comparator.reverseOrder()).collect(Collectors.toList())
				.toArray(new Integer[bankDataMaps.size()]);
		
		List<Integer> bankNoteAmounts = new ArrayList<>();
		for (Integer key : bankNoteValues) {
			bankNoteAmounts.add(bankDataMaps.get(key.toString()));
		}

		Integer withdrawAmount = request.getWithdrawAmount();

		if (withdrawAmount != null && withdrawAmount.intValue() != 0) {

			boolean isWithdraw = false;
			

			List<BankNote> bankNotes = withdraw(bankNoteValues
					,bankNoteAmounts.toArray(new Integer[bankDataMaps.size()]), withdrawAmount);

			if (bankNotes != null && !bankNotes.isEmpty()) {
				isWithdraw = true;
			}

			if (!isWithdraw) {
				response.setStatus(999);
				response.setErrorMsg(getErrorMsg(bankNoteValues,withdrawAmount));
				return response;
			}

			WithdrawData withdrawData = new WithdrawData();
			withdrawData.setWithdrawAmount(withdrawAmount);
			withdrawData.setWithdrawBankNotes(bankNotes);
			response.setData(withdrawData);

		} else {
			response.setStatus(999);
			response.setErrorMsg(getErrorMsg(bankNoteValues,withdrawAmount));
		}

		return response;
	}
	
	
	private String getErrorMsg(Integer[] bankNoteValues,Integer withdrawAmount) {
		
		
		StringJoiner joiner = new StringJoiner(", ");
		Arrays.stream(bankNoteValues).forEach(bankNote -> {
			joiner.add("฿"+bankNote.toString());
		});
		
		
		return "Sorry, available denominations are "+joiner.toString()+"!! Given amount of ฿"+withdrawAmount+" cannot be dispensed.";
		
	}

	public List<BankNote> withdraw(Integer[] bankNoteValues,Integer[] bankNoteAmount, Integer withdrawAmount) {
		List<BankNote> bankNotes = null;

		Integer[] solutions = new Integer[bankNoteValues.length];
		for (Integer i = 0; i < solutions.length; i++) {
			solutions[i] = 0;
		}

		solutions = solutions(bankNoteValues,bankNoteAmount, solutions,
				withdrawAmount);

		if (solutions != null) {
			bankNotes = new ArrayList<>();

			for (int i = 0; i < solutions.length; i++) {
				if (solutions[i] != 0) {
					String key = String.valueOf(bankNoteValues[i]);
					BankNote bankNote = new BankNote(bankNoteValues[i], solutions[i]);
					Integer bankNoteBalance = atmData.getBankNotes().get(key);
					if(bankNoteBalance.intValue() != 0) {
						atmData.getBankNotes().put(key, bankNoteBalance - solutions[i]);
					}else {
						atmData.getBankNotes().remove(key);
					}
					bankNotes.add(bankNote);
				}
			}

			TransactionsHist transactionsHist = new TransactionsHist();
			transactionsHist.setTxnSeq(dataManagement.generateNextTxnSeq());
			transactionsHist.setAtmBalance(dataManagement.getAtmBalance());
			transactionsHist.setTxnDateTime(LocalDateTime.now());
			transactionsHist.setWithdrawAmount(withdrawAmount);
			transactionsHist.setWithdrawBankNotes(bankNotes);

			dataManagement.addTxnHist(transactionsHist);
		}

		return bankNotes;

	}

	private Integer[] solutions(Integer[] bankNoteValues, Integer[] bankNoteAmount, Integer[] sol,
			Integer withdrawAmount) {

		Integer withdrawAmountTemp = withdrawAmount.intValue();

		for (Integer i = 0; i < bankNoteValues.length; i++) {

			Integer useCounter = 0;

			for (Integer j = 0; j < bankNoteAmount[i]; j++) {

				if (withdrawAmountTemp - bankNoteValues[i] >= 0) {
					withdrawAmountTemp = withdrawAmountTemp - bankNoteValues[i];
					useCounter++;	
					
					if(bankNoteAmount[i] - (useCounter - 1) == 1 
							&& withdrawAmountTemp.intValue() % bankNoteValues[i] == 0
							&& bankNoteValues.length != (i+1)
							&& withdrawAmountTemp.intValue() % bankNoteValues[i+1] != 0 ) {
						withdrawAmountTemp += bankNoteValues[i];
						useCounter--;
						break;
					}
					
					if(withdrawAmountTemp == 0) {break;}
				} else if(withdrawAmountTemp > 0){
					
					boolean isOk = false;

					Integer newTemp = withdrawAmountTemp.intValue();
					for (Integer k = i + 1; k < bankNoteValues.length; k++) {
						if (newTemp % bankNoteValues[k] == 0) {
							isOk = true;
							break;
						}else {
							newTemp = withdrawAmountTemp % bankNoteValues[k];
						}
					}

					if (!isOk && useCounter > 0) {
						withdrawAmountTemp += bankNoteValues[i];
						useCounter--;
						break;
					}

				}
			}

			sol[i] = useCounter;

			if (withdrawAmountTemp == 0) {
				break;
			}
		}

		if (withdrawAmountTemp > 0)
		{
			return null;
		} else {
			return sol;
		}
	}

}
