package com.neopragma.javadatetime.injection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * This approach is based on an idea by Luiz Signorelli.
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
	
	private LocalDateTime lastInvoiceDate;
    private LocalDateTime invoiceDate;
	
	@Before
	public void beforeEach() {
	    invoiceDate = LocalDateTime.of(2017, 6, 15, 0, 0);
	    lastInvoiceDate = invoiceDate.minusDays(10);
		history = new GuestHistory(1);
	}
	
	@Test
	public void historyReturnsChargesMadeFromTheLastInvoiceDateThroughYesterday() {
		when(chargeBeforeLastInvoiceDate.getDate()).thenReturn(invoiceDate.minusDays(11));
		when(firstChargeToInvoice.getDate()).thenReturn(lastInvoiceDate);
		when(secondChargeToInvoice.getDate()).thenReturn(invoiceDate.minusDays(1));
		when(chargeMadeToday.getDate()).thenReturn(invoiceDate);
        history.setLastInvoiceDate(lastInvoiceDate);
        history.addCharge(chargeBeforeLastInvoiceDate);
        history.addCharge(firstChargeToInvoice);
        history.addCharge(secondChargeToInvoice);
        history.addCharge(chargeMadeToday);
		List<Charge> charges = history.getCharges(invoiceDate);
		assertEquals(lastInvoiceDate, charges.get(0).getDate());
		assertEquals(invoiceDate.minusDays(1), charges.get(1).getDate());
		assertEquals(2, charges.size());
	}

}