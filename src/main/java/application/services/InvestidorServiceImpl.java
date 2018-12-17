package application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.domain.Investidor;
import application.repositories.InvestidorRepository;

@Service
public class InvestidorServiceImpl implements InvestidorService {

	
	@Autowired
	private InvestidorRepository investidorRepo;	
	
	@Override
	public List<Investidor> getAll() {
		return this.investidorRepo.findAll();
	}

	@Override
	public Investidor getById(UUID id) {
		
		Optional<Investidor> investidor = investidorRepo.findById(id);
		if (!investidor.isPresent()) {
			throw new IllegalArgumentException("Investidor não localizada com esse id: " + id);
		}
		return investidor.get();
		
	}

	@Override
	public Investidor create(Investidor investidor) {
		return this.investidorRepo.save(investidor);
	}

}
