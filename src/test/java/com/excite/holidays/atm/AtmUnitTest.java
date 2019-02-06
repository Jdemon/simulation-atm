package com.excite.holidays.atm;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringJoiner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.excite.holidays.atm.app.WithdrawApplication;
import com.excite.holidays.atm.component.DataManagement;
import com.excite.holidays.atm.model.AtmData;
import com.excite.holidays.atm.service.WithdrawService;
import com.excite.holidays.atm.service.model.Request;
import com.excite.holidays.atm.service.model.Response;
import com.excite.holidays.atm.service.model.WithdrawData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WithdrawApplication.class)
@SpringBootConfiguration
@ActiveProfiles("test")
public class AtmUnitTest {

	@Autowired
	private WithdrawService withdrawService;

	@Autowired
	private AtmData atmData;

	@Autowired
	private DataManagement dataManagement;

	@Test
	public void testWithdraw100_1() {

		setBankNote(5, 1, 0, 1, 1);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw100_1 ");

		Request request = new Request();
		request.setWithdrawAmount(100);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);

		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw100_2 ");
			System.out.println();
		}
	}
	
	@Test
	public void testWithdraw100_2() {
		
		setBankNote(5, 5, 0, 1, 1);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw100_2 ");

		Request request = new Request();
		request.setWithdrawAmount(100);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);

		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw100_2 ");
			System.out.println();
		}
		
	}
	
	@Test
	public void testWithdraw100_3() {
		
		setBankNote(5, 2, 1, 1, 1);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw100_3 ");

		Request request = new Request();
		request.setWithdrawAmount(100);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw100_3 ");
			System.out.println();
		}
	}
	
	
	@Test
	public void testWithdraw1000_1() {
		
		setBankNote(1, 20, 10, 2, 1);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw1000_1 ");

		Request request = new Request();
		request.setWithdrawAmount(1000);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw1000_1 ");
			System.out.println();
		}
	}
	
	@Test
	public void testWithdraw1000_2() {
		
		setBankNote(1, 20, 10, 2, 0);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw1000_2 ");

		Request request = new Request();
		request.setWithdrawAmount(1000);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw1000_2 ");
			System.out.println();
		}
	}
	
	@Test
	public void testWithdraw1000_3() {
		
		setBankNote(1, 20, 10, 1, 0);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw1000_3 ");

		Request request = new Request();
		request.setWithdrawAmount(1000);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw1000_3 ");
			System.out.println();
		}
	}
	
	@Test
	public void testWithdraw1000_4() {
		
		setBankNote(10, 3, 2, 1, 0);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw1000_4 ");

		Request request = new Request();
		request.setWithdrawAmount(1000);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw1000_4 ");
			System.out.println();
		}
	}
	
	@Test
	public void testWithdraw1000_5() {
		
		setBankNote(1, 1, 10, 0, 0);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw1000_5 ");

		Request request = new Request();
		request.setWithdrawAmount(1000);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw1000_5 ");
			System.out.println();
		}
	}
	
	@Test
	public void testWithdraw930_1() {
		
		setBankNote(4,3,2,1,0);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw930_1 ");

		Request request = new Request();
		request.setWithdrawAmount(930);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw930_1 ");
			System.out.println();
		}
	}
	
	@Test
	public void testWithdraw930_2() {
		
		setBankNote(100,100,100,100,100);
		
		System.out.println();
		printATMNotesAvail("---- start testWithdraw930_2 ");

		Request request = new Request();
		request.setWithdrawAmount(930);

		Response response = withdrawService.withdraw(request);
		
		printStatus(response);
		try {
			assertThat(response.getStatus()).isEqualTo(0);
		}finally {
			printATMNotesAvail("---- end testWithdraw930_2 ");
			System.out.println();
		}
	}

	private void printATMNotesAvail(String state) {
		StringJoiner joiner = new StringJoiner(", ",state+"notes [","]");
		
		dataManagement.getAtmData().getBankNotes().entrySet().forEach(bankNote -> {
			if(bankNote.getValue() != 0) {
				joiner.add("฿"+bankNote.getKey() + "=" + bankNote.getValue());
			}
		});
		
		System.out.println(joiner.toString());

	}

	private void printStatus(Response response) {

		if (response.getStatus() != 0) {
			System.err.println("error message : " + response.getErrorMsg());
		} else {
			System.out.println("success message : " + getSuccessMsg(response.getData()));
		}
	}

	private String getSuccessMsg(WithdrawData withdrawData) {

		StringJoiner joiner = new StringJoiner(", ", "Dispensing Cash ฿" + withdrawData.getWithdrawAmount() + "=", "");

		withdrawData.getWithdrawBankNotes().forEach(bankNote -> {
			joiner.add(bankNote.getBankNoteValue() + "*" + bankNote.getBankNoteAmount());
		});

		return joiner.toString();

	}

	private void setBankNote(int twtyno, int fityno, int onehundo, int fihundno, int onethouno) {

		Map<String, Integer> bankNotes = new LinkedHashMap<>();

		bankNotes.put("20", twtyno);
		bankNotes.put("50", fityno);
		bankNotes.put("100", onehundo);
		bankNotes.put("500", fihundno);
		bankNotes.put("1000", onethouno);

		atmData.setBankNotes(bankNotes);
	}

}
