package com.excite.holidays.atm.component;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.excite.holidays.atm.model.TransactionsHist;
import com.excite.holidays.atm.service.WithdrawService;
import com.excite.holidays.atm.service.model.Request;
import com.excite.holidays.atm.service.model.Response;
import com.excite.holidays.atm.service.model.WithdrawData;

@Component
@Order(3)
@Profile("dev")
public class WithdrawCommandLine implements CommandLineRunner {

	@Autowired
	private Map<String, TransactionsHist> transactionsHistMap;

	@Autowired
	private WithdrawService withdrawService;

	@Autowired
	private DataManagement dataManagement;

	@Override
	public void run(String... args) throws Exception {
		System.out.println("input amount = -1 -> stop program");
		System.out.println("input amount = -2 -> check atm balance");
		System.out.println("input amount = 0 -> see transation history");
		System.out.println("input amount > 0 -> withdraw cash");

		printATMBalance();
		printATMNotesAvail();

		Request request = new Request();

		try (Scanner scanner = new Scanner(System.in)) {

			while (true) {

				System.out.print("Atm withdraw amount : ฿");

				Integer amount = scanner.nextInt();

				if (amount == -1) {
					break;
				} else if (amount == -2) {
					printATMBalance();
					printATMNotesAvail();
					continue;
				} else if (amount == 0) {
					printTransactionHistory();
					continue;
				}

				request.setWithdrawAmount(amount);

				Response response = withdrawService.withdraw(request);

				System.out.println();
				if (response.getStatus() != 0) {
					System.err.println("----------------------------ERROR----------------------------------");
					System.err.println("status : Error");
					System.err.println("error message : " + response.getErrorMsg());
					System.err.println("----------------------------ERROR----------------------------------");
				} else {
					System.out.println("----------------------------SUCCESS----------------------------------");
					System.out.println("status : Success");
					System.out.println("message : " + getSuccessMsg(response.getData()));
					printATMBalance();
					printATMNotesAvail();
					System.out.println("----------------------------SUCCESS----------------------------------");

				}
				System.out.println();

				if (dataManagement.getAtmBalance() == 0) {
					System.out.println("ATM available balance is 0฿ : Out of Sevice");
					printTransactionHistory();
					break;

				}
			}
		}
		
		System.exit(0);
		

	}

	private void printATMBalance() {
		System.out.println("ATM Balance : ฿" + dataManagement.getAtmBalance());
	}

	private void printATMNotesAvail() {
		for (Entry<String, Integer> entry : dataManagement.getAtmData().getBankNotes().entrySet()) {

			System.out.println("฿" + entry.getKey() + " available " + entry.getValue()
					+ (entry.getValue() > 1 ? " notes" : " note") + " =฿"
					+ Integer.parseInt(entry.getKey()) * entry.getValue());

		}

	}

	private void printTransactionHistory() {
		System.out.println("--------------------------------Transaction history---------------------------");
		for (Entry<String, TransactionsHist> entry : transactionsHistMap.entrySet()) {
			System.out.println(entry.getValue());
		}
		System.out.println("--------------------------------Transaction history---------------------------");
	}

	private String getSuccessMsg(WithdrawData withdrawData) {

		StringJoiner joiner = new StringJoiner(", ", "Dispensing Cash ฿" + withdrawData.getWithdrawAmount() + "=", "");

		withdrawData.getWithdrawBankNotes().forEach(bankNote -> {
			joiner.add(bankNote.getBankNoteValue() + "*" + bankNote.getBankNoteAmount());
		});

		return joiner.toString();

	}

}
