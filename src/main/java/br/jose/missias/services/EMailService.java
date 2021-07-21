package br.jose.missias.services;

import br.jose.missias.entities.User;

public interface EMailService {
	public void notifyDelays(User user);
}
