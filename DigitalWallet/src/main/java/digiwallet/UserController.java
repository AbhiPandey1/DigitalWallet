package digiwallet;



import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
 
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.justinsb.etcd.EtcdClient;
import com.justinsb.etcd.EtcdClientException;
import com.justinsb.etcd.EtcdResult;





@SuppressWarnings("deprecation")
@RestController
public class UserController 

{
	SimpleDateFormat sdf = new SimpleDateFormat ("E yyyy.MM.dd hh:mm:ss a zzz");
    Random randomgenerator = new Random();
	
	
	ApplicationContext applicationctx = 
            new AnnotationConfigApplicationContext(configurationMongoSpring.class);
MongoOperations mongoOperation = 
            (MongoOperations) applicationctx.getBean("mongoTemplate");
 EtcdClient client = new EtcdClient(URI.create("http://54.183.240.185:4001/"));
	

	//1. User Creation
	@RequestMapping(value="/api/v1/users",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Object createUser(@Valid @RequestBody User user)
	{
		int userid =  (10000 + randomgenerator.nextInt(999999));
		user.setUser_id("u-"+userid);
		user.setCreated_at(sdf.format(new Date()));
		//userdatalist.add(user);
		mongoOperation.save(user);;
		return user;
	}
	
	@RequestMapping(value="/api/v1/users/allusers",method = RequestMethod.GET)
	@ResponseBody
	public List<User> viewuser()
	{
		List<User> alluserlist = new ArrayList<User>();
		Query query = new Query();
		query.addCriteria(null);
		 alluserlist=  mongoOperation.find(query, User.class);
		return alluserlist;
	}
	
	//2. User Display
	@RequestMapping(value="/api/v1/users/{user_id}",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Object getUserData(@PathVariable String user_id)
	{	
		Query query = new Query();
		query.addCriteria(Criteria.where("user_id").is(user_id));
		Object x =  mongoOperation.find(query, User.class);
		return x;
		
	}
	
	//3. User Updating
	@RequestMapping(value="/api/v1/users/{user_id}",method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Object updateUserData(@Valid @RequestBody User user,@PathVariable String user_id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("user_id").is(user_id));
		Object x =  mongoOperation.findOne(query, User.class);
		((User) x).setEmail(user.getEmail());
		((User) x).setPassword(user.getPassword());
		((User) x).setUpdated_at(sdf.format(new Date()));
		((User) x).setName(user.getName());
		mongoOperation.save(x);
		return x;
   	}
	
	//4. ID Card Creation
	@RequestMapping(value="/api/v1/users/{user_id}/idcards",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Object createIdForUser(@Valid @RequestBody IDCard idcard,@PathVariable String user_id)
	{
				Query query = new Query();
				query.addCriteria(Criteria.where("user_id").is(user_id));
				Object x =  mongoOperation.findOne(query, User.class);
				if(x!=null){
				int card_id =  (10000 + randomgenerator.nextInt(99999999));
				idcard.setCard_id("c-"+card_id);
				idcard.setUser_id(user_id);
				mongoOperation.save(idcard);
				return idcard;}
				else{return "User not present, IDCard cannot be created !";}
		
		}
	
	//5. ID Cards List Display
	@RequestMapping(value="/api/v1/users/{user_id}/idcards",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<IDCard> getidCardsForUser(@PathVariable String user_id)
	{
	
		
		Query query = new Query();
		query.addCriteria(Criteria.where("user_id").is(user_id));
		List<IDCard> idcardlistdisplay = mongoOperation.find(query,IDCard.class);
		return idcardlistdisplay;
		
	}
	
	//6. ID Card Deletion
	@RequestMapping(value="/api/v1/users/{user_id}/idcards/{card_id}",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public Object deleteIdCardsForUser(@PathVariable String card_id)
	{
	
		Query query = new Query();
		query.addCriteria(Criteria.where("card_id").is(card_id));
		Object x = new Object(); 
		x = mongoOperation.findOne(query, IDCard.class);
		if(x!=null){
		mongoOperation.remove(x);			
		return "Delete Succesfull";}
		else
		{
			return "ID Card provided is not available !.";
		}

		
	}
	
	//7. Web Login Creation
	@RequestMapping(value="/api/v1/users/{user_id}/weblogins",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Object createWebLoginForUser(@Valid @RequestBody WebLogin web,@PathVariable String user_id)
	{
	
	
		Query query = new Query();
		query.addCriteria(Criteria.where("user_id").is(user_id));
		Object x = new Object ();
		x=mongoOperation.find(query,User.class);
		if(x!=null)
		{
		web.setUser_id(user_id);
		int login_id =  (10000 + randomgenerator.nextInt(99999999));
		web.setLogin_id("l-"+login_id);
		mongoOperation.save(web);
		return web;
		}
		
		else
		{
		return "User not pesent, WebLogin cannot be created !";
		}
		
	}
	
	//8.  Web Logins Listing
	@RequestMapping(value="/api/v1/users/{user_id}/weblogins",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<WebLogin> getWebLoginsForUser(@PathVariable String user_id)
	{
		List<WebLogin> webblogins = new ArrayList<WebLogin>();
		Query query =new Query();
		query.addCriteria(Criteria.where("user_id").is(user_id));
		webblogins = mongoOperation.find(query, WebLogin.class);
		return webblogins;
		
	}
	
	//9. Web Login Deletions
	@RequestMapping(value="/api/v1/users/{user_id}/weblogins/{login_id}",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public Object deleteWebLoginsForUser(@PathVariable String login_id)
	{
		
		
		Query query = new Query();
		query.addCriteria(Criteria.where("login_id").is(login_id) );
		Object x = new Object();
		x = mongoOperation.findOne(query, WebLogin.class);
		if(x!=null){
		mongoOperation.remove(x);
		return "Delete was successful !";}
		else
		{
			return "Weblogin detail provided does not exist!.";
		}
		
	}
	
	//10. Bank Account Creation
	@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Object createBankAccountForUser(@Valid @RequestBody BankAccount bank,@PathVariable String user_id)
	{		
		String routingnumber = bank.getRouting_number();
	
		final String url= "http://www.routingnumbers.info/api/data.json?rn="+routingnumber;
	
		RestTemplate restTemplateExample = new RestTemplate();
  		List<HttpMessageConverter<?>> listConvertedMessage = restTemplateExample.getMessageConverters();
        MappingJackson2HttpMessageConverter formatJsonConvertedMessage = new MappingJackson2HttpMessageConverter();
		
		List<MediaType> listnewFormatType = new ArrayList<MediaType>();
		listnewFormatType.add(new MediaType("text", "plain"));
		listnewFormatType.add(new MediaType("application", "json"));
		formatJsonConvertedMessage.setSupportedMediaTypes(listnewFormatType);
		listConvertedMessage.add(formatJsonConvertedMessage);
		restTemplateExample.setMessageConverters(listConvertedMessage);
		
		
		lookupAPIBankAccount lookupAPI = restTemplateExample.getForObject(url,lookupAPIBankAccount.class);
		Query query = new Query();
		query.addCriteria(Criteria.where("user_id").is(user_id));
		Object x  = new Object();
		x = mongoOperation.find(query, User.class);
		
		
		if (x!=null)
		{
				if(lookupAPI.getCode() == 200){
				int ba_id = (10000 + randomgenerator.nextInt(99999999));
				bank.setBa_id("b-"+ba_id);
				bank.setUser_id(user_id);
				bank.setAccount_name(lookupAPI.getCustomer_name());
				mongoOperation.save(bank);
				return bank;
				
		}
				
				else 
				{
				return lookupAPI;
				} 
				
		}
		
		else
		{
			return "User not found !";
		}

	}
	
	//11. Bank Accounts Listing
	@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public List<BankAccount> getBankAccountsForUser(@PathVariable String user_id)
	{
		Query query = new Query();
		query.addCriteria(Criteria.where("user_id").is(user_id));
		List<BankAccount> bankaccounts = new ArrayList<BankAccount>();
		bankaccounts = mongoOperation.find(query, BankAccount.class);
		return bankaccounts;
		
	}
	
	//12. Bank Account Deletion
	@RequestMapping(value="/api/v1/users/{user_id}/bankaccounts/{ba_id}",method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ResponseBody
	public Object deleteBankAccountForUser(@PathVariable String user_id,@PathVariable String ba_id)
	{
	
	
		Query query = new Query();
		query.addCriteria(Criteria.where("ba_id").is(ba_id));
		Object x = new Object();
		x=mongoOperation.findOne(query,BankAccount.class);
		if (x!=null)
		{
			mongoOperation.remove(x);
			return "Delete done !";
		}
		
		else
		{
			return "BankAccount details provided does not exist";
		}
		
}
	
	@RequestMapping(value="/api/v1/counter",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String getUserData() throws EtcdClientException
	{	
		String keyValueOfCounter = "/009979151/counter";
		EtcdResult result = new EtcdResult();
		result = client.get(keyValueOfCounter);
		int CountInitial = Integer.parseInt(result.node.value);
		int CountFinal = CountInitial + 1;
		String stringFinalCount = CountFinal+"";
		EtcdResult update = this.client.set(keyValueOfCounter,stringFinalCount);
		result = this.client.get(keyValueOfCounter);
		return result.node.value;
		
			}

	@RequestMapping(value="/api/v1/health",method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String getHealth() 
	{	
		return "";
		
			}
}