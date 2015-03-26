package digiwallet;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.mongodb.DB;
import com.mongodb.MongoClient;
 
/**
 * Spring MongoDB configuration file
 * 
 */
@Configuration
public class configurationMongoSpring{
 
	public @Bean
	MongoTemplate mongoTemplate() throws Exception {
 
		MongoClient clientForMongo = new MongoClient("ds049170.mongolab.com:49170");
		DB db = clientForMongo.getDB("assignment2mongo273");
		boolean auth = db.authenticate("ncakpan", "1234.@Abhi".toCharArray());
		MongoTemplate templateForMongo = new MongoTemplate(clientForMongo,"assignment2mongo273");
		return templateForMongo;
		
/*		MongoTemplate mongoTemplate = 
			new MongoTemplate(new MongoClient("127.0.0.1"),"mydb");
		return mongoTemplate;
 
*/	}
 
}