package services;

import java.util.Set;

import domain.Empresa;


public interface EmpresaService {

	Set<Empresa> getAll();

	Empresa getById(String id);

	Empresa addEmpresa(Empresa company);

	Empresa save(String id, Empresa company);

	void deleteById(String id);
	
}
