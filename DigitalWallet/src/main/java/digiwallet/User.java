package digiwallet;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "users")
public class User implements Serializable {

	@Id
	private String id;  
	//@Indexed
    private  String user_id;
   @NotEmpty
    private  String email;
   @NotEmpty
    private  String password;
   @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private  String name;
    private  String created_at;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private  String updated_at;
    
    public User(String user_id, String email, String password, String name,
			String created_at, String updated_at) {
		super();
		this.user_id = user_id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getCreated_at() {
		return created_at;
	}

	public String getUpdated_at() {
		return updated_at;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public void setUpdated_at(String updated_at) {
		this.updated_at = updated_at;
	}
    
    
}