package sl.ms.inventorymanagement.config;

import java.util.function.BiFunction;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;
import org.springframework.web.client.ResourceAccessException;

import sl.ms.inventorymanagement.dto.Student;
import sl.ms.inventorymanagement.exception.KafkaCustomErrorHandler;

@Service
public class KafkaConsumer {

	@KafkaListener(topics = {"test"},groupId = "1212")
	public void consumeMessage(ConsumerRecord<String, Student> payload) {
		System.out.println("Kafka messages from test...." +payload.value() );
	}
	
	@KafkaListener(topics = {"partition-test"},groupId = "1212")
	public void consumeMessage1(ConsumerRecord<String, String> payload) {
		System.out.println("Kafka messages from partition-test from group 1212...." +payload.value() );
	}
	
	@KafkaListener(topics = {"partition-test"},groupId = "1212")
	public void consumeMessage2(ConsumerRecord<String, String> payload) {
		System.out.println("Kafka messages from partition-test from group 1212 from 2nd method...." +payload.value() );
	}
	/*
	 * @KafkaListener(topics = {"partition-test"},groupId = "121234") public void
	 * consumeMessage2(ConsumerRecord<String, String> payload) {
	 * System.out.println("Kafka messages from partition-test from group 121234...."
	 * +payload.value() ); }
	 */
	
	@KafkaListener(topics = {"test1"},groupId = "1212", filter = "filterEventsByMessage")
	public void consumeMessage3(ConsumerRecord<String, String> payload) {
		System.out.println("Kafka messages from test1...." +payload.value() );
	}
	
	@Bean
	public DefaultErrorHandler error() {
		BackOff backOff=new FixedBackOff(1000L, 4L);
	BiFunction<ConsumerRecord<?, ?>, Exception, BackOff> backOffFunction=(a,b)->backOff;
		DefaultErrorHandler defaultErrorHandler=new KafkaCustomErrorHandler();
	//defaultErrorHandler.setBackOffFunction(backOffFunction);
		//defaultErrorHandler.addRetryableExceptions(ResourceNotFoundException.class);
		//defaultErrorHandler.addNotRetryableExceptions(NullPointerException.class,ResourceAccessException.class);
	//DefaultErrorHandler error=new DefaultErrorHandler(backOff)	;
	return defaultErrorHandler;
	}
}
