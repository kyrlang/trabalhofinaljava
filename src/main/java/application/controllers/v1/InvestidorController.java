package application.controllers.v1;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.domain.Investidor;
import application.services.InvestidorService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api/v1/investidores")
@Api(value = "InvestidorController", description = "Operações realizdas pelo investidor")
public class InvestidorController {

	private InvestidorService investidorService;

    public InvestidorController(InvestidorService investidorService) {
        this.investidorService = investidorService;
    }
    
	// GET
	@GetMapping("")
	public ResponseEntity<List<Investidor>> getAll() {
		List<Investidor> investidores = this.investidorService.getAll();
		return new ResponseEntity<List<Investidor>>(investidores, HttpStatus.OK);
	}    
	
    @GetMapping("/{id}")
    public ResponseEntity<Investidor> findById(@PathVariable UUID id) {
    	Investidor investidor = this.investidorService.getById(id);
        return new ResponseEntity<Investidor>(investidor, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Investidor> create(@RequestBody Investidor investidor) {
    	investidor.setId(UUID.randomUUID());
    	investidor.setName(investidor.getName());
    	investidor.setEmail(investidor.getEmail());
		Investidor novoInvestidor = investidorService.create(investidor);
		return new ResponseEntity<Investidor>(novoInvestidor, HttpStatus.OK);
    }	
	
	
}
