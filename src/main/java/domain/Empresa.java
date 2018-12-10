package domain;

import java.sql.Timestamp;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document
public class Empresa {


	@Id
	private String id = UUID.randomUUID().toString();
	private String name;
	private String email;
	private String timestamp = new Timestamp(System.currentTimeMillis()).toString();

	
}
