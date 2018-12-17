package application.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Estoque {
	
    @Id
    private UUID id;
    private String name;
    private Integer qtdAcoes;
    private float valorAcao;
    private String status;
   
    @DBRef
    private Empresa empresa;	
		
}
