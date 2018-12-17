package application.bootstrap;

import java.util.UUID;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import application.domain.Empresa;
import application.repositories.EmpresaRepository;

@Component
public class ApplicationBootstrap implements ApplicationListener<ContextRefreshedEvent> {

	private EmpresaRepository empresaRepo;

	public ApplicationBootstrap(EmpresaRepository categoryRepository) {
		this.empresaRepo = categoryRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		if (empresaRepo.count() == 0L) {
			empresaRepo.deleteAll();
			loadCategories();
		}
	}

	private void loadCategories() {
		Empresa emp1 = new Empresa();
		emp1.setId(UUID.randomUUID());
		emp1.setEmail("email1");
		emp1.setName("Nome1");
		empresaRepo.save(emp1);

		Empresa emp2 = new Empresa();
		emp2.setId(UUID.randomUUID());
		emp2.setEmail("email2");
		emp2.setName("Nome2");
		empresaRepo.save(emp2);

		Empresa emp3 = new Empresa();
		emp3.setId(UUID.randomUUID());
		emp3.setEmail("email2");
		emp3.setName("Nome");
		empresaRepo.save(emp3);
	}
}
