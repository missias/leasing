package br.jose.missias.services;

import static br.jose.missias.utils.DateUtils.getDateWitDifferenceOfDays;
import static br.jose.missias.utils.DateUtils.isSameDate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Date;

import org.junit.Test;

import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;

public class LeasingServiceTest {
	@Test
	public void test() {
		/*
		 * principles of test FIRST
		(F)ast
		(I)dependent
		(R)epeatable
		(S)elf-Verifying
		(T)imely
		*/
		 //scenario
		LeasingService service = new LeasingService();
		User user = new User("user");
		Movie movie = new Movie("movie 1", 1, 5.0);
		
		// action 
		
		Leasing leasing = service.alugarFilme(user, movie);
		
		 //validation
		//Assert.assertEquals(5.0, leasing.getValue(), 0.01);  
		assertThat(leasing.getValue(), is(equalTo(5.0)));
 
		assertThat(isSameDate(leasing.getRentDate() , new Date()), is(true));
		assertThat(isSameDate(leasing.getReturnDate() , getDateWitDifferenceOfDays(1) ), is(true)  );
		
	}
}
