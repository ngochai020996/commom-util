/**
 * 
 */
package com.axonactive.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author nvmuon
 *
 */
public class DateTimeUtils {

	/**
	 * yyyy-MM-dd
	 */
	public static final String APP_DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd'T'HH:mm:ss.SSSX
	 */
	public static final String APP_DATETIME_APTTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSX";

	private DateTimeUtils() {
	}

	public static final SimpleDateFormat getSimpleDateFormat() {
		return new SimpleDateFormat(APP_DATETIME_APTTERN);
	}

	public static final String formatDatetime(Date date) {
		return DateTimeUtils.getSimpleDateFormat().format(date);
	}

	public static final Date parseDatetime(String source) throws ParseException {
		return DateTimeUtils.getSimpleDateFormat().parse(source);
	}
	
	public static final Date parseDatetime(String source, String pattern) throws ParseException {
		return new SimpleDateFormat(pattern).parse(source);
	}

	public static Date createDate(int year, int month, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, date);
		return calendar.getTime();
	}

	public static String format(Date date, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}
}
