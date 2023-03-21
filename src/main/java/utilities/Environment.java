package utilities;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

// đường dẫn trỏ vào properties

//resources ( package ) 
//@Sources({"classpath:dev.properties"}) 

//folder 
//@Sources({"file:environmentConfig/dev.properties"})

//dynamic environment 
@Sources({"file:environmentConfig/${env}.properties"})
public interface Environment extends Config {
	
	// Hàm getter 
	@Key("App.Url")
	String appUrl();
	
	
	@Key("App.User")
	String appUser();
	
	
	@Key("App.Pass")
	String appPass();
	
	
	@Key("DB.Host")
	String dbHost();
	
	
	@Key("DB.User")
	String dbUser();
	
	@Key("DB.Pass")
	String dbPass();

}
