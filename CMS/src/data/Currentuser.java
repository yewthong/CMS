package data;

public class Currentuser {
	
    private String ICPassport;
    private String name;
    private String password;
    private char gender;
    private String address;
    private String hp;
    private String email;
    private String role;
    
    
    public Currentuser(String iCPassport, String name, String password, char gender, String address, String hp,
			String email, String role) {
		this.ICPassport = iCPassport;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.hp = hp;
		this.email = email;
		this.role = role;
	}
    
    public Currentuser() {
    	
    }
    
	public String getICPassport() {
		return ICPassport;
	}
	public void setICPassport(String iCPassport) {
		ICPassport = iCPassport;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
    
    
	

}
