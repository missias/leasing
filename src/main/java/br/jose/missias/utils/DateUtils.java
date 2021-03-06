package br.jose.missias.utils;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	
	/**
	 * Returns the date sent by parameter with the addition of the desired days 
	 * the Date can be in the future (days > 0) or in the past (days < 0)
	 * 	 
	 * 
	 * @param date
	 * @param days 
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(DAY_OF_MONTH, days);
		return calendar.getTime();
	}
	
	/**
	 * Returns the current date with the difference of days sent by parameter the Date 
	 * can be in the future (positive parameter) or in the past (negative parameter)
	 * 		 
	 * 
	 * @param days Number of days to be increased/decreased
	 * @return Date updated
	 */
	public static Date getDateWitDifferenceOfDays(int dias) {
		return addDays(new Date(), dias);
	}
	
	/**
	 * Returns an instance of <code>Date</code> reflecting values passed by parameter
	 * 
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	public static Date getDate(int day, int month, int year){
		Calendar calendar = Calendar.getInstance();
		calendar.set(DAY_OF_MONTH, day);
		calendar.set(MONTH, month - 1);
		calendar.set(YEAR, year);
		return calendar.getTime();
	}
	
	/**
	 * Checks if one date is the same as another 
	 * This comparison considers only day, month and year
	 *  
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean isSameDate(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		return (calendar1.get(DAY_OF_MONTH) == calendar2.get(DAY_OF_MONTH))
				&& (calendar1.get(MONTH) == calendar2.get(MONTH))
				&& (calendar1.get(YEAR) == calendar2.get(YEAR));
	}
	
	/**
	 * Check if a certain date is the desired day of the week
	 * 
	 * @param date <code>Date</code> to be evaluated
	 * @param dayOfWeek <code>true</code> if it is the desired day of the week, <code>false</code> otherwise 
	 * @return
	 */
	public static boolean verifyDayOfWeek(Date date, int dayOfWeek) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(DAY_OF_WEEK) == dayOfWeek;
	}
}

