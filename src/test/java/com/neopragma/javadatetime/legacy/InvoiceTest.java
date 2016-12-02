package com.neopragma.javadatetime.legacy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.neopragma.javadatetime.legacy.Charge;
import com.neopragma.javadatetime.legacy.GuestHistory;

@RunWith(MockitoJUnitRunner.class)
public class InvoiceTest {

// Change these values to reflect the current date before running tests!
	private int year = 2016;
	private int month = 11;
	private int day = 2;
	
	
	private GuestHistory history;
	@Mock Charge chargeBeforeLastInvoiceDate;
	@Mock Charge chargeOnLastInvoiceDate;
	@Mock Charge firstChargeToInvoice;
	@Mock Charge secondChargeToInvoice;
	@Mock Charge chargeMadeToday;
	
	private Date lastInvoiceDate;
	private Date beforeLastInvoiceDate;
	private Date firstDateToInvoice;
	private Date secondDateToInvoice;
	private Date today;
	
	@Before
	public void beforeEach() {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		today = cal.getTime();
		cal.add(Calendar.DATE, -10);
		lastInvoiceDate = cal.getTime();
		cal.setTime(lastInvoiceDate);
		cal.add(Calendar.DATE, -1);
		beforeLastInvoiceDate = cal.getTime();	
		firstDateToInvoice = lastInvoiceDate;
		cal.setTime(today);
		cal.add(Calendar.DATE, -1);
		secondDateToInvoice = cal.getTime();
		history = new GuestHistory(1);
	}
	
	@Test
	public void historyReturnsChargesMadeFromTheLastInvoiceDateThroughYesterday() {
        history.setLastInvoiceDate(lastInvoiceDate);
        history.addCharge(chargeBeforeLastInvoiceDate);
        history.addCharge(firstChargeToInvoice);
        history.addCharge(secondChargeToInvoice);
        history.addCharge(chargeMadeToday);
		when(chargeBeforeLastInvoiceDate.getDate()).thenReturn(beforeLastInvoiceDate);
		when(firstChargeToInvoice.getDate()).thenReturn(firstDateToInvoice);
		when(secondChargeToInvoice.getDate()).thenReturn(secondDateToInvoice);
		when(chargeMadeToday.getDate()).thenReturn(today);
		List<Charge> charges = history.getCharges();
		assertEquals(firstDateToInvoice, charges.get(0).getDate());
		assertEquals(secondDateToInvoice, charges.get(1).getDate());
		assertEquals(2, charges.size());
	}
	
}
