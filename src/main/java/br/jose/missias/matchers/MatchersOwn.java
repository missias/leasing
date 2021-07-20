package br.jose.missias.matchers;

import java.util.Calendar;

public class MatchersOwn {
	
	public static DayOfWeekMatcher onDay(Integer dayOfWeek) {
		return new DayOfWeekMatcher(dayOfWeek);
	}
	
	public static DayOfWeekMatcher onMonday() {
		return new DayOfWeekMatcher(Calendar.MONDAY);
	}

}
