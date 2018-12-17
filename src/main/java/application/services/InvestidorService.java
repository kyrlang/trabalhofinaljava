package application.services;

import java.util.List;
import java.util.UUID;

import application.domain.Investidor;

public interface InvestidorService {

	List<Investidor> getAll();
	Investidor getById(UUID id);
	Investidor create(Investidor investidor);
	
}
