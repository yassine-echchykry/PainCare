package PainCare.Beans;



import java.sql.Date;

public class User_Bean {
	private int id;
	private String name;
	private String email;
	
	
	public int getID() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	
	public void setID(int id) {
		this.id = id;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public void setEmail(String value) {
		this.email = value;
	}
	
	
}

