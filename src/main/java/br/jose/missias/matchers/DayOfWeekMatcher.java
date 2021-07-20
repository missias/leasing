package br.jose.missias.matchers;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.jose.missias.utils.DateUtils;

public class DayOfWeekMatcher extends TypeSafeMatcher<Date> {

	
	private Integer dayOfWeek;
	
	public DayOfWeekMatcher(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public void describeTo(Description arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean matchesSafely(Date date) {
		return DateUtils.verifyDayOfWeek(date, this.dayOfWeek);
	}

}
