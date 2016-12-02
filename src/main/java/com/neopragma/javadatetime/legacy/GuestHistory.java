package com.neopragma.javadatetime.legacy;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GuestHistory {

	private int customerId;
	private Date lastInvoiceDate;
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
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);
		List<Charge> result = new ArrayList<Charge>(0);
		for (Charge charge : charges) {
			if (charge.getDate().getTime() >= lastInvoiceDate.getTime()
			&&  charge.getDate().getTime() < today.getTime().getTime()) {
				result.add(charge);
			}
		}
		return result;
	}
	
	public void addCharge(Charge charge) {
		charges.add(charge);
	}
	
	public void setLastInvoiceDate(Date lastInvoiceDate) {
		this.lastInvoiceDate = lastInvoiceDate;
	}
	
	public Date getLastInvoiceDate() {
		return lastInvoiceDate;
	}
	
	public int getCustomerId() {
		return customerId;
	}

}
