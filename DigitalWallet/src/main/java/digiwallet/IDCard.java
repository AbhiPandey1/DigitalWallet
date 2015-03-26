package digiwallet;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "idcards")
public class IDCard implements Serializable {

	@Id
	private String id;
    @JsonIgnore
    private  String user_id;
    private  String card_id;
    @NotEmpty
    private  String card_name;
    @NotEmpty
    private  String card_number;
    @JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
    private  String expiration_date;
    
	public IDCard(String user_id, String card_id, String card_name,
			String card_number, String expiration_date) {
		
		super();
		this.user_id = user_id;
		this.card_id = card_id;
		this.card_name = card_name;
		this.card_number = card_number;
		this.expiration_date = expiration_date;
	}

	public IDCard()
    {}
	
	public String getUser_id() {
		return user_id;
	}

	public String getCard_id() {
		return card_id;
	}

	public String getCard_name() {
		return card_name;
	}

	public String getCard_number() {
		return card_number;
	}

	public String getExpiration_date() {
		return expiration_date;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public void setExpiration_date(String exp_date) {
		this.expiration_date = exp_date;
	}
    
	
    
}