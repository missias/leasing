package br.jose.missias.matchers;

import static br.jose.missias.utils.DateUtils.getDateWitDifferenceOfDays;
import static br.jose.missias.utils.DateUtils.isSameDate;

import java.util.Date;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class DifferenceOfDaysMatcher extends TypeSafeMatcher<Date> {

	private Integer days;
	
	public DifferenceOfDaysMatcher(Integer days) {
		this.days = days;
	}

	public void describeTo(Description description) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean matchesSafely(Date date) {
		return isSameDate(date,  getDateWitDifferenceOfDays(days));
	}

}
