package com.ntk.constant;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateTimeConstant {
    private DateTimeConstant(){}

	public static final DateTimeFormatter ddMMyyyyFormatter = new DateTimeFormatterBuilder()
																.appendPattern("dd/MM/yyyy")                   
																.parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
																.parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
																.parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
																.parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
																.toFormatter();
    public static String getLocalTimeNow() {
		 // Get the current LocalDateTime
		 LocalDateTime now = LocalDateTime.now();
        
		 // Define the format
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		 
		 // Convert LocalDateTime to String
		 String formattedDateTime = now.format(formatter);
         return formattedDateTime;
	}

	public static LocalDateTime convertLocalDateTime(String strDate, DateTimeFormatter dateFormatter) {
		return  LocalDateTime.parse(strDate, dateFormatter);
	}
}
