package application.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Empresa {

	@Id
	private UUID id;
	private String name;
	private String email;
	
    @DBRef
    private List<Estoque> estoques = new ArrayList<Estoque>();	
	
}
