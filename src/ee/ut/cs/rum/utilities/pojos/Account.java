package ee.ut.cs.rum.utilities.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="accounts")
public class Account {
	
	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	private Long id;

	@Column(name = "name")
    private String name;
	@Column(name = "email")
    private String email;
	@Column(name = "password")
    private String password;
	@Column(name = "role")
    private String role;
    
    public Account() {
	}
    
    public Long getId() {
        return id;
    }

	public void setId(Long id) {
		this.id = id;
	}
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return "Account [id=" + id + 
        		", mane=" + name + 
        		", email=" + email + 
        		", password=" + password + 
        		", role=" + role + "]";
	}
    
}
