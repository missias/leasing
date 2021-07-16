package br.jose.missias.entities;

import java.util.Date;
import java.util.List;

public class Leasing {

	private User user;
	private List<Movie> movies;
	private Date rentDate;
	private Date returnDate;
	private Double value;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

 

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public Date getRentDate() {
		return rentDate;
	}

	public void setRentDate(Date rentDate) {
		this.rentDate = rentDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

}