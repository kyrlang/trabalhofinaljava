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

import application.domain.Empresa;
import application.services.EmpresaService;
import io.swagger.annotations.Api;


@RestController
@RequestMapping("/api/v1/empresas")
@Api(value = "EmpresaController", description = "Operações realizdas pela empresa")
public class EmpresaController {

	private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }
	
	// GET
	@GetMapping("")
	public ResponseEntity<List<Empresa>> getAll() {
		List<Empresa> empresas = this.empresaService.getAll();
		return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
	}
	
    @GetMapping("/{id}")
    public ResponseEntity<Empresa> findById(@PathVariable UUID id) {
    	Empresa empresa = this.empresaService.getById(id);
        return new ResponseEntity<Empresa>(empresa, HttpStatus.OK);
    }
    
    @GetMapping("/name/{name}")
    public ResponseEntity<List<Empresa>> findByName(@PathVariable String name) {
    	List<Empresa> empresas = this.empresaService.getByName(name);
    	return new ResponseEntity<List<Empresa>>(empresas, HttpStatus.OK);
    }    

    @PostMapping("")
    public ResponseEntity<Empresa> create(@RequestBody Empresa empresa) {
    	empresa.setId(UUID.randomUUID());
		empresa.setName(empresa.getName());
		empresa.setEmail(empresa.getEmail());
		Empresa novaEmpresa = empresaService.create(empresa);
		return new ResponseEntity<Empresa>(novaEmpresa, HttpStatus.OK);
    }	
	
}
