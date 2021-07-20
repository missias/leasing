package br.jose.missias.matchers;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import br.jose.missias.utils.DateUtils;

public class DayOfWeekMatcher extends TypeSafeMatcher<Date> {

	
	private Integer dayOfWeek;
	
	public DayOfWeekMatcher(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	public void describeTo(Description desc) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, dayOfWeek);
		String displayName = cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, new Locale("pt", "BR"));
		desc.appendText(displayName);
	}

	@Override
	protected boolean matchesSafely(Date date) {
		return DateUtils.verifyDayOfWeek(date, this.dayOfWeek);
	}

}
