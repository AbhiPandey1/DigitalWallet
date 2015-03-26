package digiwallet;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class lookupAPIBankAccount

{
	public int code;
	@JsonIgnore
	public String message;
	@JsonIgnore
	public String change_date;
	@JsonIgnore
	public String office_code;
	@JsonIgnore
	public String record_type_code;
	@JsonIgnore
	public String new_routing_number;
	@JsonIgnore
	public String state;
	@JsonIgnore
	public String address;
	@JsonIgnore
	public String telephone;
	@JsonIgnore
	public String data_view_code;
	@JsonIgnore
	public String city;
	@JsonIgnore
	public String institution_status_code;
	@JsonIgnore
	public String zip;
	
	public String routing_number;
	@JsonIgnore
	public String account_number;
	public String customer_name;
	
	public String getRouting_number() {
		return routing_number;
	}
	public void setRn(String routing_number) {
		this.routing_number = routing_number;
	}
	
	public int getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getChange_date() {
		return change_date;
	}
	public void setChange_date(String change_date) {
		this.change_date = change_date;
	}
	public String getOffice_code() {
		return office_code;
	}
	public void setOffice_code(String office_code) {
		this.office_code = office_code;
	}
	public String getRecord_type_code() {
		return record_type_code;
	}
	public void setRecord_type_code(String record_type_code) {
		this.record_type_code = record_type_code;
	}
	public String getNew_routing_number() {
		return new_routing_number;
	}
	public void setNew_routing_number(String new_routing_number) {
		this.new_routing_number = new_routing_number;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getData_view_code() {
		return data_view_code;
	}
	public void setData_view_code(String data_view_code) {
		this.data_view_code = data_view_code;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getInstitution_status_code() {
		return institution_status_code;
	}
	public void setInstitution_status_code(String institution_status_code) {
		this.institution_status_code = institution_status_code;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public void setRouting_number(String routing_number) {
		this.routing_number = routing_number;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	
	
	
}