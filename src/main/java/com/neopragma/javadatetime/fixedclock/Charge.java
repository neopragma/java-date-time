package com.neopragma.javadatetime.fixedclock;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.neopragma.javadatetime.fixedclock.TimeMachine;

public class Charge {
	
	private LocalDateTime date;
	private BigDecimal amount;
	
	public Charge(BigDecimal amount) {
		this.date = TimeMachine.now();
		this.amount = amount;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public BigDecimal getAmount() {
		return amount;
	}


}
