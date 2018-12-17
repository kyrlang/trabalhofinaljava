package application.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.domain.Estoque;
import application.repositories.EstoqueRepository;
import application.config.RabbitMQConfig;

@Service
public class EstoqueServiceImpl implements EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepo;

	private final RabbitTemplate rabbitTemplate;	
	public EstoqueServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
	}
	
	@Override
	public List<Estoque> getAll() {
		return this.estoqueRepo.findAll();
	}

	@Override
	public Estoque getById(UUID id) {
		Optional<Estoque> estoque = estoqueRepo.findById(id);
		if (!estoque.isPresent()) {
			throw new IllegalArgumentException("Estoque não localizado com esse id: " + id);
		}
		return estoque.get();
	}

	@Override
	public Estoque create(Estoque estoque) {
		return this.estoqueRepo.save(estoque);
	}

	@Override
	public void emitir(UUID idEstoque, int quantidadeAcoes) {
		Optional<Estoque> estoqueAtual = estoqueRepo.findById(idEstoque);
		if (estoqueAtual.isPresent()) {
			int qtdAcoesAtual = estoqueAtual.get().getQtdAcoes() + quantidadeAcoes;
			estoqueAtual.get().setQtdAcoes(qtdAcoesAtual);
			this.estoqueRepo.save(estoqueAtual.get());
		}
	}

	@Override
	public Estoque delete(String status, UUID id) {
		Optional<Estoque> estoqueAtual = estoqueRepo.findById(id);
		estoqueAtual.get().setStatus("vendido");
		this.estoqueRepo.save(estoqueAtual.get());
		return estoqueAtual.get();
		
		
	}

	@Override
	public void sendMensagem(Estoque estoque) {
		this.rabbitTemplate.convertAndSend(RabbitMQConfig.COMPRA_FILA, estoque);
	}

}
