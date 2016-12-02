package com.neopragma.javadatetime.prejava8;

public class DefaultTimeSource implements TimeSource {

	/**
	 * Return the current time
	 */
	public long currentTimeMillis() {
		return System.currentTimeMillis();
	}
}
