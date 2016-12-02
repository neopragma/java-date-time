package com.neopragma.javadatetime.injection;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class GuestHistory {

	private int customerId;
	private LocalDateTime lastInvoiceDate;
	private List<Charge> charges;
	
	public GuestHistory(int customerId) {
		this.customerId = customerId;
		this.charges = new ArrayList<Charge>();
	}

	/**
	 * Returns all charges that fall between the last invoice date
	 * and yesterday's date.
	 * 
	 * @return charges to be invoiced
	 */
	public List<Charge> getCharges(LocalDateTime invoiceDate) {
		List<Charge> result = new ArrayList<Charge>(0);
		for (Charge charge : charges) {
			if (charge.getDate().isAfter(lastInvoiceDate.minusDays(1))
			&&  charge.getDate().isBefore(invoiceDate)) {
				result.add(charge);
			}
		}
		return result;
	}
	
	public void addCharge(Charge charge) {
		charges.add(charge);
	}
	
	public void setLastInvoiceDate(LocalDateTime lastInvoiceDate) {
		this.lastInvoiceDate = lastInvoiceDate;
	}
	
	public LocalDateTime getLastInvoiceDate() {
		return lastInvoiceDate;
	}
	
	public int getCustomerId() {
		return customerId;
	}

}
