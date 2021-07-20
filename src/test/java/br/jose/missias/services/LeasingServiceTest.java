package br.jose.missias.services;

import static br.jose.missias.utils.DateUtils.getDateWitDifferenceOfDays;
import static br.jose.missias.utils.DateUtils.isSameDate;
import static br.jose.missias.utils.DateUtils.verifyDayOfWeek;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.exceptions.LeasingException;
import br.jose.missias.exceptions.MovieWithoutStockException;
import br.jose.missias.matchers.DayOfWeekMatcher;
import br.jose.missias.utils.DateUtils;

public class LeasingServiceTest {
	/*
	 * principles of test FIRST (F)ast (I)dependent (R)epeatable (S)elf-Verifying
	 * (T)imely
	 */

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private LeasingService service;
	
	@Before
	public void setup() {
		service = new LeasingService();
	}

	@After
	public void tearDown() {
		
	}
	
	@Test
	public void shouldRentAMovie() throws Exception {

		Assume.assumeFalse(verifyDayOfWeek(new Date(), Calendar.SATURDAY));
		
		// scenario
		
		User user = new User("user");
		List<Movie> movies =  Arrays.asList( new Movie("movie 1", 2, 5.0));

		// action

		Leasing leasing = service.rentMovie(user, movies);

		// validation

		// Assert.assertEquals(5.0, leasing.getValue(), 0.01);
		/*
		 * assertThat(leasing.getValue(), is(equalTo(5.0)));
		 * assertThat(isSameDate(leasing.getRentDate() , new Date()), is(true));
		 * assertThat(isSameDate(leasing.getReturnDate() , getDateWitDifferenceOfDays(1)
		 * ), is(true) );
		 */
		// Use error Collector when a method has more than an assertive

		error.checkThat(leasing.getValue(), is(equalTo(5.0)));
		error.checkThat(isSameDate(leasing.getRentDate(), new Date()), is(true));
		error.checkThat(isSameDate(leasing.getReturnDate(), getDateWitDifferenceOfDays(1)), is(true));

	}

	/*
	 * elegant approach I prefer this one approach
	 */
	@Test(expected = MovieWithoutStockException.class)
	public void ShouldThrowAExceptionOnTryToRentMovieWithoutStock() throws Exception {

		// scenario
		User user = new User("user");
		List<Movie> movies =  Arrays.asList( new Movie("movie 1", 0, 5.0));

		// action

		service.rentMovie(user, movies);

		// validation

	}

	/*
	 * robust approach
	 * This one is more complete
	 */
	@Test
	public void ShouldThrowAExceptionOnTryToRentMovieWithoutStock_2() {

		// scenario
		User user = new User("user");
		List<Movie> movies =  Arrays.asList( new Movie("movie 1", 0, 5.0));

		// action

		try {
			service.rentMovie(user, movies);
			Assert.fail("Would have been thrown an exception");
		} catch (Exception e) {
			assertThat(e.getMessage(), is("Movie without stock"));
		}

		// validation
		System.out.println("Robust approach");
	}

	/*
	 * Using a Rule to handle exceptions in tests
	 */

	@Test
	public void ShouldThrowAExceptionOnTryToRentMovieWithoutStock_3() throws Exception {

		// scenario
		User user = new User("user");
		List<Movie> movies =  Arrays.asList( new Movie("movie 1", 0, 5.0));

		exception.expect(Exception.class);
		exception.expectMessage("Movie without stock");
		// action

		service.rentMovie(user, movies);

		// validation

	}

	@Test
	public void shouldNotRentAMovieWithoutUser() throws MovieWithoutStockException {
		// scenario
		List<Movie> movies =  Arrays.asList( new Movie("movie 1", 2, 5.0));

		// action

		try {
			service.rentMovie(null, movies);
			Assert.fail("Would have been thrown an exception");
		} catch (LeasingException e) {
			assertThat(e.getMessage(), is("invalid user"));
		}

		// validation

	}

	@Test
	public void shouldNoRentAMovieWithoutAMovie() throws MovieWithoutStockException, LeasingException {
		 //scenario
		User user = new User("user");
		// action
		exception.expect(LeasingException.class);
		exception.expectMessage("invalid movie");
		
		service.rentMovie(user, null);
		// validation

	}
	

	@Test
	public void ShouldReturnAmovieOnMondayIfRentOnSaturday() throws MovieWithoutStockException, LeasingException {
		
		Assume.assumeTrue(verifyDayOfWeek(new Date(), Calendar.SATURDAY));
		
		 //scenario
			User user = new User("user");
			
			List<Movie> movies =  Arrays.asList( new Movie("movie 1", 2, 5.0));
			

		 //action
		   Leasing	result = service.rentMovie(user, movies);
			//4+4+3+2+1+0=14
		 //validation
		  // boolean isMonday = verifyDayOfWeek(result.getReturnDate(), Calendar.MONDAY);
		   //assertTrue(isMonday);
		   
		   
		   assertThat(result.getReturnDate(), new DayOfWeekMatcher(Calendar.MONDAY) );
	}
	
	

}
