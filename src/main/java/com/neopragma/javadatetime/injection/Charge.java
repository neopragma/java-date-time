package com.neopragma.javadatetime.injection;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Charge {
	
	private LocalDateTime date;
	private BigDecimal amount;
	
	public Charge(LocalDateTime chargeDate, BigDecimal amount) {
		this.date = chargeDate;
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}


}
