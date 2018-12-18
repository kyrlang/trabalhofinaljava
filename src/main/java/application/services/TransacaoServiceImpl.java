package application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.config.EmailSender;
import application.domain.Estoque;
import application.domain.Transacao;
import application.repositories.TransacaoRepository;

@Service
public class TransacaoServiceImpl implements TransacaoService {

	@Autowired
	private TransacaoRepository transacaoRepo;
	
	@Override
	public List<Transacao> getAll() {
		return this.transacaoRepo.findAll();
	}

	@Override
	public Transacao getById(UUID id) {
		Optional<Transacao> transacao = transacaoRepo.findById(id);
		if (!transacao.isPresent()) {
			throw new IllegalArgumentException("Empresa não localizada com esse id: " + id);
		}
		return transacao.get();
	}

	@Override
	public Transacao compra(Transacao transacao) {
		UUID idEstoque = transacao.getEstoque().getId();

		Transacao transacaoEstoque =  this.transacaoRepo.findByEstoque(idEstoque);
		
		if (transacaoEstoque == null) {
			transacao.setId(UUID.randomUUID());
			
			EmailSender email = new EmailSender();
			email.sendEmail(transacao.getVendedor().getEmail(), "Ação vendida", "Ação vendida");
			
			return this.transacaoRepo.save(transacao);
		}else {
			throw new IllegalArgumentException("Esse lote de acoes já foi comprado por outro investidor: " + transacao.getEstoque().getId());
		}
	}

	@Override
	public Estoque venda(Transacao transacao) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate();
		EstoqueService estoqueService = new EstoqueServiceImpl(rabbitTemplate);
		transacao.getEstoque().setId(UUID.randomUUID()); 
		transacao.getEstoque().setStatus("disponivel");
		EmailSender email = new EmailSender();
		email.sendEmail(transacao.getVendedor().getEmail(), "Ação vendida", "Ação vendida");
		return estoqueService.create(transacao.getEstoque());
	}

}
