package application.services;


import java.util.List;
import java.util.UUID;

import application.domain.Empresa;


public interface EmpresaService {

	List<Empresa> getAll();
	List<Empresa> getByName(String name);
	Empresa getById(UUID id);
	Empresa create(Empresa emp);

}
