package sl.ms.inventorymanagement.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.ProducerFactory;

import sl.ms.inventorymanagement.dto.Student;

@Configuration 
public class KafkaProducerConfig {
	@Autowired
	KafkaConfig kafkaConfig;
	
	/*
	 * public ProducerFactory<String, Student> producerFactory(){
	 * 
	 * Map<String, Object> props=new HashMap<>();
	 * props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	 * kafkaConfig.getProducer().getBootstrapServer());
	 * 
	 * }
	 */
}
