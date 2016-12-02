package com.neopragma.javadatetime.fixedclock;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.neopragma.javadatetime.fixedclock.TimeMachine;

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
	public List<Charge> getCharges() {
		LocalDateTime today = TimeMachine.now();
		List<Charge> result = new ArrayList<Charge>(0);
		for (Charge charge : charges) {
			if (charge.getDate().isAfter(lastInvoiceDate.minusDays(1))
			&&  charge.getDate().isBefore(today)) {
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
