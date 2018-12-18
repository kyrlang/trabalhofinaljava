package application.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Investidor {

	   @Id
	    private UUID id;
	    private String name;
	    private String email;
	
}
