package domain;

import java.sql.Timestamp;
import java.util.UUID;

public class Investidor {

	private String id = UUID.randomUUID().toString();
	private String name;
	private String email;
	private String timestamp = new Timestamp(System.currentTimeMillis()).toString();
	
	public Investidor(String name, String email) {
		this.setId(getId());
		this.setEmail(email);
		this.setName(name);
		this.setTimestamp(getTimestamp());
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}	
	
}
