package br.jose.missias.services;

import static br.jose.missias.utils.DateUtils.getDateWitDifferenceOfDays;
import static br.jose.missias.utils.DateUtils.isSameDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.exceptions.LeasingException;
import br.jose.missias.exceptions.MovieWithoutStockException;

public class LeasingServiceTest {
	/*
	 * principles of test FIRST (F)ast (I)dependent (R)epeatable (S)elf-Verifying
	 * (T)imely
	 */

	@Rule
	public ErrorCollector error = new ErrorCollector();

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void leasingTest() throws Exception {

		// scenario
		LeasingService service = new LeasingService();
		User user = new User("user");
		Movie movie = new Movie("movie 1", 2, 5.0);

		// action

		Leasing leasing = service.rentMovie(user, movie);

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
	public void leasingTest_withoutStock() throws Exception {

		// scenario
		LeasingService service = new LeasingService();
		User user = new User("user");
		Movie movie = new Movie("movie 1", 0, 5.0);

		// action

		service.rentMovie(user, movie);

		// validation

	}

	/*
	 * robust approach
	 * This one is more complete
	 */
	@Test
	public void leasingTest_withoutStock_2() {

		// scenario
		LeasingService service = new LeasingService();
		User user = new User("user");
		Movie movie = new Movie("movie 1", 0, 5.0);

		// action

		try {
			service.rentMovie(user, movie);
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
	public void leasingTest_withoutStock_3() throws Exception {

		// scenario
		LeasingService service = new LeasingService();
		User user = new User("user");
		Movie movie = new Movie("movie 1", 0, 5.0);

		exception.expect(Exception.class);
		exception.expectMessage("Movie without stock");
		// action

		service.rentMovie(user, movie);

		// validation

	}

	@Test
	public void invalidUserTest() throws MovieWithoutStockException {
		// scenario
		LeasingService service = new LeasingService();
		Movie movie = new Movie("movie 1", 1, 5.0);

		// action

		try {
			service.rentMovie(null, movie);
			Assert.fail("Would have been thrown an exception");
		} catch (LeasingException e) {
			assertThat(e.getMessage(), is("invalid user"));
		}

		// validation

	}

	@Test
	public void invalidMovieTest() throws MovieWithoutStockException, LeasingException {
		 //scenario
		LeasingService service = new LeasingService();
		User user = new User("user");
		// action
		exception.expect(LeasingException.class);
		exception.expectMessage("invalid movie");
		
		service.rentMovie(user, null);
		// validation
		
		

	}

}
