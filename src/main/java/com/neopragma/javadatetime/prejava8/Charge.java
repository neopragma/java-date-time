package com.neopragma.javadatetime.prejava8;

import java.math.BigDecimal;
import java.util.Date;

public class Charge {
	
	private Date date;
	private BigDecimal amount;
	
	public Charge(Date chargeDate, BigDecimal amount) {
		this.date = chargeDate;
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}


}
