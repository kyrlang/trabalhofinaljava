package services;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import domain.Empresa;
import repositories.EmpresaRepository;

public class EmpresaServiceImpl implements EmpresaService {
	
	private EmpresaRepository empresaRepo;

	@Override
	public Set<Empresa> getAll() {
		Set<Empresa> empresas = new HashSet<>();
		empresaRepo.findAll().iterator().forEachRemaining(empresas::add);
		return empresas;
	}

	@Override
	public Empresa getById(String id) {
		return getEmpresaById(id);
	}
	
	private Empresa getEmpresaById(String id) {
		Optional<Empresa> empresa = empresaRepo.findById(id);
	
		if(!empresa.isPresent())
			throw new IllegalArgumentException("Empresa não localizada com esse id: " + id);

		return empresa.get();
	}	

	@Override
	public Empresa addEmpresa(Empresa empresa) {
		
		if (empresaRepo.findByNome(empresa.getName()).isEmpty()) {
			return empresaRepo.save(empresa);
		}
		else {
			throw new IllegalArgumentException("Já existe ua empresa com esse nome " + empresa.getName());
		}
	}

	@Override
	public Empresa save(String id, Empresa empresa) {
		empresa.setId(id);
		return empresaRepo.save(empresa);
	}

	@Override
	public void deleteById(String id) {
		empresaRepo.deleteById(id);
	}
	

}
