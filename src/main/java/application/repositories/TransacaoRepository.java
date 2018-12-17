package application.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import application.domain.Transacao;

@Repository
public interface TransacaoRepository extends MongoRepository<Transacao, UUID> {

	Transacao findByEstoque(UUID idEstoque);
	
}
