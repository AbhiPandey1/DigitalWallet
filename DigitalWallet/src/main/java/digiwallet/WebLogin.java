package digiwallet;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "weblogins")
public class WebLogin implements Serializable {

	@Id
	private String id;
	
	@JsonIgnore
    private  String user_id;
   	private  String login_id;
   	@NotEmpty
    private  String url;
   	@NotEmpty
    private  String login;
   	@NotEmpty
    private  String password;
    
    public WebLogin()
    {}
    
    public WebLogin(String user_id, String login_id, String url, String login,
			String password) {
		super();
		this.user_id = user_id;
		this.login_id = login_id;
		this.url = url;
		this.login = login;
		this.password = password;
	}

	public String getUser_id() {
		return user_id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public String getUrl() {
		return url;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
}