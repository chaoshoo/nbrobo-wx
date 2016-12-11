package com.net.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class DateSupport {

	/**
	 * Converts the character date to a date in the specified format
	 * 
	 * @param date
	 *            Date string
	 * @param format
	 *            Date format
	 * @return
	 * @author tangshengyu
	 * @version falvm
	 * @date Dec 9, 2009
	 * @return Date
	 */
	public static Date strForDate(String date, String format) {

		Date dateTime = null;

		if (date == null || "".equals(date)) {

			return dateTime;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		try {
			dateTime = formatter.parse(date);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dateTime;

	}

	/**
	 * A string that will be converted to a specified format
	 * 
	 * @param date
	 *            Date,
	 * @param format
	 *            Date format
	 * @return
	 * @author tangshengyu
	 * @version falvm
	 * @date Dec 9, 2009
	 * @return String
	 */
	public static String dateForString(Date date, String format) {

		String dateTime = "";

		if (date == null) {

			return dateTime;
		}

		SimpleDateFormat formatter = new SimpleDateFormat(format);

		dateTime = formatter.format(date);

		return dateTime;
	}
	
	public static String datePlusDay(String strDate,int plus){
		Date date = strForDate(strDate, "yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int D = calendar.get(Calendar.DAY_OF_MONTH);
		
		date.setDate(D+plus);
		return dateForString(date, "yyyy-MM-dd");
	}
	
	public static Calendar getDateCalendar(String strDate){
		
		Date date = strForDate(strDate, "yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		
		return calendar;
	}
	/**
	 * Get the maximum value of the month
	 * 
	 * @param strDate
	 * @return
	 * @author tangshengyu
	 * @version panyu
	 * @date	Jul 16, 2010 
	 * @return  int
	 */
	public static int getDateMonthLastDay(String strDate){
		Calendar calendar = getDateCalendar(strDate);
		return calendar.getActualMaximum(Calendar.MONTH);
	}

}