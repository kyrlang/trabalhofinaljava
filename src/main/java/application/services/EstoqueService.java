package application.services;

import java.util.List;
import java.util.UUID;

import application.domain.Estoque;

public interface EstoqueService {

	List<Estoque> getAll();
	Estoque getById(UUID id);
	Estoque create(Estoque estoque);
	Estoque delete(String status, UUID id);
	void emitir(UUID idEstoque, int quantidadeAcoes);
	void sendMensagem(Estoque estoque);
	
}
