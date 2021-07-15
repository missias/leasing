package br.jose.missias.services;

import java.util.Date;

import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.utils.DateUtils;

public class LeasingService {
	
	public Leasing alugarFilme(User user, Movie movie) {
		Leasing leasing = new Leasing();
		leasing.setMovie(movie);
		leasing.setUser(user);
		leasing.setRentDate(new Date());
		leasing.setValue(movie.getRentPrice() );

		//Next day delivery
		Date returnDate = new Date();
		returnDate = DateUtils.addDays(returnDate, 1);
		leasing.setReturnDate(returnDate);
		
		//Saving the location...
		//TODO add method to save
		
		return leasing;
	}

	public static void main(String[] args) {
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
		System.out.println(leasing.getValue() == 5.0);
		System.out.println(DateUtils.isSameDate(leasing.getRentDate() , new Date()));
		System.out.println(DateUtils.isSameDate(leasing.getReturnDate() , DateUtils.getDateWitDifferenceOfDays(1) )  );
		
	}
}
