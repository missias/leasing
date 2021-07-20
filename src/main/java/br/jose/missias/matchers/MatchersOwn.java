package br.jose.missias.matchers;

import java.util.Calendar;

public class MatchersOwn {
	
	public static DayOfWeekMatcher on(Integer dayOfWeek) {
		return new DayOfWeekMatcher(dayOfWeek);
	}
	
	public static DayOfWeekMatcher onMonday() {
		return new DayOfWeekMatcher(Calendar.MONDAY);
	}
	
	public static DifferenceOfDaysMatcher isTodayWitDifferenceOfDays(Integer days) {
		return new DifferenceOfDaysMatcher(days);
		
	}
	
	public static DifferenceOfDaysMatcher isToday() {
		return new DifferenceOfDaysMatcher(0);
	}

}
