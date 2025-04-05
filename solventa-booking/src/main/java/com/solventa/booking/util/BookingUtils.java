package com.solventa.booking.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class BookingUtils {
	
	private static final DateTimeFormatter FORMATTER_HOUR = DateTimeFormatter.ofPattern("dd/MM/yyyy: HH:mm:ss");
	private static final DateTimeFormatter FORMATTER_DATE = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	

	/**
	 * Formats a LocalDateTime object to a string in the format "dd/MM/yyyy: HH:mm:ss".
	 *
	 * @param dateTime the LocalDateTime object to format
	 * @return the formatted string
	 */
	public static String formatLocalDateTime(LocalDateTime dateTime) {
		return dateTime.format(FORMATTER_HOUR);
	}
	
	/**
	 * Formats a LocalDateTime object to a string in the format "dd/MM/yyyy".
	 *
	 * @param dateTime the LocalDateTime object to format
	 * @return the formatted string
	 */
	public static String formatLocalDate(LocalDateTime dateTime) {
		return dateTime.format(FORMATTER_DATE);
	}
	
	   
	 public static String formatLocalDate(Date date, boolean includeTime) {
	        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	        return includeTime ? formatLocalDateTime(localDateTime) : formatLocalDate(localDateTime);
	    }
}
