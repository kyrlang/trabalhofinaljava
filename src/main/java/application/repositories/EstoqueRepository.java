package application.repositories;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import application.domain.Estoque;

@Repository
public interface EstoqueRepository extends MongoRepository<Estoque, UUID> {

}
