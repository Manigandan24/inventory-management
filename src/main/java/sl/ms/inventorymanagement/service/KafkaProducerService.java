package sl.ms.inventorymanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import sl.ms.inventorymanagement.dto.Student;

@Service
public class KafkaProducerService {
	
	@Autowired
	private KafkaTemplate<String, Student> kafkaTemplate;
	
	public void sendMessage() {
		kafkaTemplate.send("test", new Student("Manigandan","kandan"));
	}
	
}
