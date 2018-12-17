package application.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import application.domain.Investidor;

@Repository
public interface InvestidorRepository extends MongoRepository<Investidor, UUID>{

	List<Investidor> findByName(String name);
	
}	
	
	

