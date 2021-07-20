package br.jose.missias.builders;

import br.jose.missias.entities.Movie;

public class MovieBuilder {
	
	private Movie movie;
	
	private MovieBuilder() {
		
	}

	public static MovieBuilder aMovie() {
		MovieBuilder builder = new MovieBuilder();
		builder.movie = new Movie("Movie 1", 2, 4.0);
		return builder;
		
	}
	
	public static MovieBuilder aMovieWithoutStock() {
		MovieBuilder builder = new MovieBuilder();
		builder.movie = new Movie("Movie 1", 2, 4.0);
		return builder;
		
	}
	
	public MovieBuilder withoutStock() {
		this.movie.setStock(0);
		return this;
	}
	
	public MovieBuilder withValue(Double value) {
		this.movie.setRentPrice(value);
		return this;
	}
	
	public Movie now() {
		return movie;
	}
	
}
