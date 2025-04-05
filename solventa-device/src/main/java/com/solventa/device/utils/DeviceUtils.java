package com.solventa.device.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeviceUtils {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy: HH:mm:ss");
	

	/**
	 * Formats a LocalDateTime object to a string in the format "dd/MM/yyyy: HH:mm:ss".
	 *
	 * @param dateTime the LocalDateTime object to format
	 * @return the formatted string
	 */
	public static String formatLocalDateTime(LocalDateTime dateTime) {
		return dateTime.format(FORMATTER);
	}

}
