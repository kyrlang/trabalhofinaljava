package repositories;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import domain.Empresa;

public interface EmpresaRepository extends MongoRepository<Empresa, String>{

	List<Empresa> findByNome(String nome);
	
}
