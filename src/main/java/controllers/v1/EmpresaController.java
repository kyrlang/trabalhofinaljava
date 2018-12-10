package controllers.v1;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import domain.Empresa;
import services.EmpresaService;


@RestController
@RequestMapping(EmpresaController.BASE_URL)
public class EmpresaController {


	public static final String BASE_URL = "/api/v1/empresas";
		
	@Autowired
	private EmpresaService empresaService;

	// GET
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Set<Empresa> getAll() {
		Set<Empresa> empresa = new HashSet<>();
		empresaService.getAll().forEach((Empresa emp) -> {
			empresa.add(emp);
		});
		return empresa;
	}

	@GetMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public Empresa getById(@PathVariable String id) {
		return empresaService.getById(id);
	}
	
	// POST
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Empresa createNew(@RequestBody Empresa emp) {
		Empresa empresa = empresaService.addEmpresa(emp);
		return empresa;
	}

	// PUT
	@PutMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public Empresa update(@PathVariable String id, @RequestBody Empresa emp) {
		Empresa empresa = empresaService.getById(id);
		empresa.setName(emp.getName());
		empresa.setEmail(emp.getEmail());
		return empresaService.save(id, empresa);
	}

	// DELETE
	@DeleteMapping({ "/{id}" })
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable String id) {
		empresaService.deleteById(id);
	}	
	
}
