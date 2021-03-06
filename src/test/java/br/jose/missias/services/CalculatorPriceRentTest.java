package br.jose.missias.services;

import static br.jose.missias.builders.MovieBuilder.aMovie;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.Mockito;

import br.jose.missias.builders.MovieBuilder;
import br.jose.missias.dao.LeasingDao;
import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.exceptions.LeasingException;
import br.jose.missias.exceptions.MovieWithoutStockException;


/*
 * 
 * Data driven test
 * Parameterized 
 */
@RunWith(Parameterized.class)
public class CalculatorPriceRentTest {
	
	
	private LeasingService service;
	
	@Parameter
	public List<Movie> movies;
	
	@Parameter(value=1)
	public Double priceRent;
	
	@Parameter(value=2)
	public String descr;
	
	@Before
	public void setup() {
		service = new LeasingService();
		LeasingDao dao = Mockito.mock(LeasingDao.class);
		IndebtedService indebtedService = Mockito.mock(IndebtedService.class);
		service.setDao(dao);		
		service.setIndebtedService(indebtedService);
	}
	
	private static Movie m1 = aMovie().now();
	private static Movie m2 = aMovie().now();
	private static Movie m3 = aMovie().now();
	private static Movie m4 = aMovie().now();
	private static Movie m5 = aMovie().now();
	private static Movie m6 = aMovie().now();
	private static Movie m7 = aMovie().now();
	
	@Parameters(name="Test  {2}")
	public static Collection<Object[]> getParameters() {
		return Arrays.asList(new Object[][] {
			{Arrays.asList( m1, m2 ), 8.0, "without discount"},
			{Arrays.asList( m1, m2, m3), 11.0, "3 movies 25% discount"},
			{Arrays.asList( m1, m2, m3,m4), 13.0, "4 movies 50% discount"},
			{Arrays.asList( m1, m2, m3,m4, m5), 14.0, "5 movies 75% discount"},
			{Arrays.asList( m1, m2, m3,m4, m5, m6), 14.0, "6 movies 100% discount"},
			{Arrays.asList( m1, m2, m3,m4, m5, m6,m7), 18.0, "7 movies without discount"}
		});
	}
	
	@Test
	public void ShouldCalculateTheAmountOfRentEvenIfDiscount() throws MovieWithoutStockException, LeasingException {
		 //scenario
			User user = new User("user");
	 
		 //action
		   Leasing	result = service.rentMovie(user, movies);
			
		 //validation
		   
		   assertThat(result.getValue(), is(priceRent));
		
		
	}
}
