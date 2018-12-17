package application.repositories;


import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import application.domain.Empresa;

@Repository
public interface EmpresaRepository extends MongoRepository<Empresa, UUID>{

	List<Empresa> findByName(String name);
	
}
