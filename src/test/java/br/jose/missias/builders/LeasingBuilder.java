package br.jose.missias.builders;

import static br.jose.missias.builders.MovieBuilder.aMovie;
import static br.jose.missias.builders.UserBuilder.aUser;
import static br.jose.missias.utils.DateUtils.getDateWitDifferenceOfDays;

import java.util.Arrays;
import java.util.Date;

import br.jose.missias.entities.Leasing;

public class LeasingBuilder {
	
	private Leasing leasing;
 
	private LeasingBuilder() {
		
	}

	public static LeasingBuilder aRent( ) {
		LeasingBuilder builder = new LeasingBuilder();
		initFields(builder);
		return builder;
	}
	
	private static void initFields(LeasingBuilder builder) {
		builder.leasing = new Leasing();
		builder.leasing.setUser(aUser().now());
		builder.leasing.setMovies(Arrays.asList(aMovie().now()));
		builder.leasing.setRentDate(new Date());
		builder.leasing.setReturnDate(getDateWitDifferenceOfDays(1)); 
		builder.leasing.setValue(4.0);
	}
	
	public Leasing now() {
		return this.leasing;
	}
	
}
