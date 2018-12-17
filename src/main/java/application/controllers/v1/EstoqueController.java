package application.controllers.v1;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.domain.Estoque;
import application.domain.Transacao;
import application.services.EstoqueService;
import application.services.TransacaoService;
import application.services.TransacaoServiceImpl;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/estoque")
@Api(value = "EstoqueController", description = "Operações realizdas pelo estoque")
public class EstoqueController {

	private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }
	
	// GET
	@GetMapping("")
	public ResponseEntity<List<Estoque>> getAll() {
		List<Estoque> estoque = this.estoqueService.getAll();
		return new ResponseEntity<List<Estoque>>(estoque, HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Estoque> findById(@PathVariable UUID id) {
    	Estoque estoque = this.estoqueService.getById(id);
        return new ResponseEntity<Estoque>(estoque, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Estoque> create(@RequestBody Estoque estoque) {
    	estoque.setId(UUID.randomUUID());
    	estoque.setEmpresa(estoque.getEmpresa());
    	estoque.setQtdAcoes(estoque.getQtdAcoes());
    	estoque.setValorAcao(estoque.getValorAcao());
    	estoque.setName(estoque.getName());
		Estoque novoEstoque = estoqueService.create(estoque);
		return new ResponseEntity<Estoque>(novoEstoque, HttpStatus.OK);
    }
    
    @PutMapping("/emitir/{idEstoque}")
    public ResponseEntity<String> emitirNovasAcoes(@PathVariable UUID idEstoque, @RequestBody int quantidadeAcoes) {
		estoqueService.emitir(idEstoque, quantidadeAcoes);
		return new ResponseEntity<String>("Ações atualizdas", HttpStatus.OK);
    }    
	
    @PostMapping("/compra")
    public ResponseEntity<Transacao> compraAcao(@RequestBody Transacao transacao) {
    	TransacaoService transacaoService = new TransacaoServiceImpl();
		Transacao novaTransacao = transacaoService.compra(transacao);
		
		if (novaTransacao != null) {
			estoqueService.delete("vendido", novaTransacao.getEstoque().getId());
		}
		estoqueService.sendMensagem(transacao.getEstoque());
		return new ResponseEntity<Transacao>(novaTransacao, HttpStatus.OK);
    }
    
	
}
