package application.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Transacao {

    @Id
    private UUID id;
    private LocalDateTime dataTransacao;

    @DBRef
    private Empresa vendedor;

    @DBRef
    private Investidor comprador;

    @DBRef
    private Estoque estoque;	
	
}
