package com.neopragma.javadatetime.prejava8;

public interface TimeSource {
	  static final Long DAY = (long) (24*60*60*1000);
	  long currentTimeMillis();
} 
