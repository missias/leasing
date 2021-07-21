package br.jose.missias.builders;

import br.jose.missias.entities.User;

public class UserBuilder {

	private User user;
	
	private UserBuilder() {
		
	}
	
	public static UserBuilder aUser() {
		UserBuilder builder = new UserBuilder();
		builder.user = new User("User 1");
		return builder;
	}
	
	public User now() {
		return user;
	}
	
	public UserBuilder withName(String name) {
		this.user.setName(name);
		return this;
	}
	
}
