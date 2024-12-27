package sl.ms.inventorymanagement.config;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.stereotype.Component;

import sl.ms.inventorymanagement.dto.Student;

@Component
public class FilterEventsByMessage implements RecordFilterStrategy<String, Student>{

	@Override
	public boolean filter(ConsumerRecord<String, Student> consumerRecord) {
		return consumerRecord.value().getFirstName().equals("XXXX")?true:false;
	}



}
