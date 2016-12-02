package com.neopragma.javadatetime.legacy;

import java.math.BigDecimal;
import java.util.Date;

public class Charge {
	
	private Date date;
	private BigDecimal amount;
	
	public Charge(BigDecimal amount) {
		this.date = new Date();
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}


}
