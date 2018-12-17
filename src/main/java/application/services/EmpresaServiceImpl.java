package application.services;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import application.domain.Empresa;
import application.repositories.EmpresaRepository;

@Service
public class EmpresaServiceImpl implements EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepo;
	
	@Override
	public List<Empresa> getAll() {
		return this.empresaRepo.findAll();
	}
	

	@Override
	public Empresa getById(UUID id) {
		Optional<Empresa> empresa = empresaRepo.findById(id);
		if (!empresa.isPresent()) {
			throw new IllegalArgumentException("Empresa não localizada com esse id: " + id);
		}
		return empresa.get();
	}
	
	@Override
	public List<Empresa> getByName(String name) {
		List<Empresa> empresas = empresaRepo.findByName(name);
		
		if (empresas.isEmpty()) {
			throw new IllegalArgumentException("Não existe nenhuma empresa com o nome pesquisado: " + name);
		}
		return empresas;
	}

	@Override
	public Empresa create(Empresa emp) {
		return empresaRepo.save(emp);
	}
		

}
