package br.jose.missias.services;

import java.util.Date;

import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.utils.DateUtils;
 

public class LeasingService {
	
	public Leasing alugarFilme(User user, Movie movie) throws Exception {
		
		if (movie.getStock() == 0) {
			throw new Exception("Without stock");
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
