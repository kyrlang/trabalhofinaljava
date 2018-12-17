package application.services;

import java.util.List;
import java.util.UUID;

import application.domain.Estoque;
import application.domain.Transacao;

public interface TransacaoService {

	List<Transacao> getAll();
	Transacao getById(UUID id);
	Transacao compra(Transacao transacao);
	Estoque venda(Transacao transacao);
	
}
