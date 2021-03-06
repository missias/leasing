package br.jose.missias.services;

import static br.jose.missias.utils.DateUtils.addDays;
import static br.jose.missias.utils.DateUtils.verifyDayOfWeek;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import br.jose.missias.dao.LeasingDao;
import br.jose.missias.entities.Leasing;
import br.jose.missias.entities.Movie;
import br.jose.missias.entities.User;
import br.jose.missias.exceptions.LeasingException;
import br.jose.missias.exceptions.MovieWithoutStockException;
 

public class LeasingService {
	
	
	private LeasingDao dao;
	private IndebtedService indebtedService;
	private EMailService emailService;
 
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
		
		if (indebtedService.isIndebted(user)) {
			throw new LeasingException("User is negativated");
		}
		
		Leasing leasing = new Leasing();
		leasing.setMovies(movies);
		leasing.setUser(user);
		leasing.setRentDate(new Date());
		
		Double totalAmount = 0d;
		for (int i=0; i < movies.size(); i++) {
			
			Movie movie = movies.get(i);
			Double price = movie.getRentPrice();
			switch(i) {
			case 2: price = price *  0.75; break;
			case 3: price = price *  0.50; break;
			case 4: price = price *  0.25; break;
			case 5: price = 0d; break;
 
			}
			
			totalAmount += price;
		}
		
		leasing.setValue(totalAmount );

		//Next day delivery
		Date returnDate = new Date();
		returnDate = addDays(returnDate, 1);
		
		if(verifyDayOfWeek(returnDate, Calendar.SUNDAY)) {
			returnDate = addDays(returnDate, 1);
		}
		
		
		leasing.setReturnDate(returnDate);
		
		dao.save(leasing);
		
		return leasing;
	}

	public void notifyDelays() {
		List<Leasing> leasings = dao.getPendingRents();
		for (Leasing leasing : leasings) {
			emailService.notifyDelays(leasing.getUser());
			
			
		}
	}
	
	public void setDao(LeasingDao dao) {
		this.dao = dao;
	}

	public void setIndebtedService(IndebtedService indebtedService) {
		this.indebtedService = indebtedService;
	}

	public void setEmailService(EMailService emailService) {
		this.emailService = emailService;
	}
	
	


}
