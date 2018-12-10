package bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import domain.Empresa;
import repositories.EmpresaRepository;

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
        emp1.setEmail("email1");
        emp1.setName("Nome1");
        
        Empresa emp2 = new Empresa();
        emp2.setEmail("email2");
        emp2.setName("Nome2");
        
        Empresa emp3 = new Empresa();
        emp3.setEmail("email2");
        emp3.setName("Nome");        
	}
}
