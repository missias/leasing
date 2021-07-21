package br.jose.missias.dao;

import java.util.List;

import br.jose.missias.entities.Leasing;

public interface LeasingDao {
	
	public void save(Leasing leasing);

	public List<Leasing> getPendingRents();

}
