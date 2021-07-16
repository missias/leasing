package br.jose.missias.services;

import java.util.Date;

import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.exceptions.LeasingException;
import br.jose.missias.exceptions.MovieWithoutStockException;
import br.jose.missias.utils.DateUtils;
 

public class LeasingService {
	
 
	public Leasing rentMovie(User user, Movie movie) throws MovieWithoutStockException,  LeasingException{
		
		if (movie == null) {
			throw new LeasingException("invalid movie");
		}
		if (movie.getStock() == 0) {
			throw new MovieWithoutStockException("Movie without stock");
		}
		
		if (user == null) {
			throw new LeasingException("invalid user");
		}
		
		
		
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


}
