package com.neopragma.javadatetime.prejava8;

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


/**
 * This approach is based on an idea by John O'Hanley.
 * @author dave
 *
 */

@RunWith(MockitoJUnitRunner.class)
public class InvoiceTest {
	
	private GuestHistory history;
	@Mock Charge chargeBeforeLastInvoiceDate;
	@Mock Charge chargeOnLastInvoiceDate;
	@Mock Charge firstChargeToInvoice;
	@Mock Charge secondChargeToInvoice;
	@Mock Charge chargeMadeToday;
	
	private Date lastInvoiceDate;
    private Date invoiceDate;
    private Date yesterday;
    private TimeSource today = new CurrentDate();
	
	@Before
	public void beforeEach() {
	    invoiceDate = new Date(today.currentTimeMillis());
	    lastInvoiceDate = new Date(today.currentTimeMillis()-(TimeSource.DAY*10));
	    yesterday = new Date(today.currentTimeMillis()-(TimeSource.DAY));
		history = new GuestHistory(1);
	}
	
	@Test
	public void historyReturnsChargesMadeFromTheLastInvoiceDateThroughYesterday() {
		when(chargeBeforeLastInvoiceDate.getDate())
		    .thenReturn(new Date(today.currentTimeMillis()-(TimeSource.DAY*11)));
		when(firstChargeToInvoice.getDate())
		    .thenReturn(lastInvoiceDate);
		when(secondChargeToInvoice.getDate())
	        .thenReturn(yesterday);
		when(chargeMadeToday.getDate())
		    .thenReturn(invoiceDate);
        history.setLastInvoiceDate(lastInvoiceDate);
        history.addCharge(chargeBeforeLastInvoiceDate);
        history.addCharge(firstChargeToInvoice);
        history.addCharge(secondChargeToInvoice);
        history.addCharge(chargeMadeToday);
		List<Charge> charges = history.getCharges(invoiceDate);
		assertEquals(lastInvoiceDate, charges.get(0).getDate());
		assertEquals(yesterday, charges.get(1).getDate());
		assertEquals(2, charges.size());
	}
	
	class CurrentDate implements TimeSource {
		public long currentTimeMillis() {
			Calendar now = Calendar.getInstance();
			now.set(2017, 6, 15);
			return now.getTimeInMillis();
		}
	}

}