package digiwallet;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "bankaccounts")
public class BankAccount implements Serializable{

	@Id
	private String id;
	
	@JsonIgnore
    private  String user_id;
    private  String ba_id;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private  String account_name;
    @NotEmpty
    private  String routing_number;
    @NotEmpty
    private  String account_number;
    
	public BankAccount(String user_id, String ba_id, String account_name,
			String routing_number, String account_number) {
		super();
		this.user_id = user_id;
		this.ba_id = ba_id;
		this.account_name = account_name;
		this.routing_number = routing_number;
		this.account_number = account_number;
		
		
	}

	public BankAccount()
	{}
	
	public String getUser_id() {
		return user_id;
	}

	public String getBa_id() {
		return ba_id;
	}

	public String getAccount_name() {
		return account_name;
	}

	public String getRouting_number() {
		return routing_number;
	}

	public String getAccount_number() {
		return account_number;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setBa_id(String ba_id) {
		this.ba_id = ba_id;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}

	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
    
}