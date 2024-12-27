package sl.ms.inventorymanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;


@EnableKafka
@EnableCaching
@SpringBootApplication
public class InventoryManagement {

	public static void main(String[] args) {
		//SpringApplication.run(InventoryManagement.class);
		SpringApplication app=new SpringApplication(InventoryManagement.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}
}
