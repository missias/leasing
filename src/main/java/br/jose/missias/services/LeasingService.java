package br.jose.missias.services;

import java.util.Date;
import java.util.List;

import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.exceptions.LeasingException;
import br.jose.missias.exceptions.MovieWithoutStockException;
import br.jose.missias.utils.DateUtils;
 

public class LeasingService {
	
 
	public Leasing rentMovie(User user, List<Movie> movies) throws MovieWithoutStockException,  LeasingException{
		
		if (user == null) {
			throw new LeasingException("invalid user");
		}
		
		if ( (movies == null) || (movies.isEmpty())) {
			throw new LeasingException("invalid movie");
		}
		
		for (Movie movie : movies) {
			if (movie.getStock() == 0) {
				throw new MovieWithoutStockException("Movie without stock");
			}
		}
		

		
		
		
		Leasing leasing = new Leasing();
		leasing.setMovies(movies);
		leasing.setUser(user);
		leasing.setRentDate(new Date());
		
		Double totalAmount = 0d;
		for (Movie movie : movies) {
			totalAmount += movie.getRentPrice();
		}
		
		leasing.setValue(totalAmount );

		//Next day delivery
		Date returnDate = new Date();
		returnDate = DateUtils.addDays(returnDate, 1);
		leasing.setReturnDate(returnDate);
		
		//Saving the location...
		//TODO add method to save
		
		return leasing;
	}


}
