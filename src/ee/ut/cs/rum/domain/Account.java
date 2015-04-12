package ee.ut.cs.rum.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="accounts")
public class Account implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	private Long id;
	
	@NotNull
    @Size(min = 2, max = 24)
	@Column(name = "name", unique=true)
    private String name;
	
	@NotNull
	@Email
	@Column(name = "email")
    private String email;
	
	@NotNull
    @Size(min = 2, max = 24)
    @Column(name = "password")
	private String password;
	
	@NotNull
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
