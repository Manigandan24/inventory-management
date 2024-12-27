package sl.ms.inventorymanagement.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.kafka.support.serializer.DeserializationException;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

public class KafkaCustomErrorHandler extends DefaultErrorHandler{

	@Override
	public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer,
			MessageListenerContainer container, boolean batchListener) {
		BackOff backOff=new FixedBackOff(1000L, 2L);
		System.out.println("!!!!!!!!!Inside OtherException!!!!!!!!!!!!!!!");
		BiFunction<ConsumerRecord<?, ?>, Exception, BackOff> backOffFunction=(a,b)->backOff;
		setBackOffFunction(backOffFunction);
		super.handleOtherException(thrownException, consumer, container, batchListener);
	}
	
	@Override
	public void handleRemaining(Exception thrownException, List<ConsumerRecord<?, ?>> records, Consumer<?, ?> consumer,
			MessageListenerContainer container) {
		System.out.println("!!!!!!!!!Inside Remaining!!!!!!!!!!!!!!!");
		BackOff backOff=new FixedBackOff(1000L, 3L);
		BiFunction<ConsumerRecord<?, ?>, Exception, BackOff> backOffFunction=(a,b)->backOff;
		setBackOffFunction(backOffFunction);
		Map<Class<? extends Throwable>, Boolean> classifications=new HashMap<>();
		classifications.put(DeserializationException.class, Boolean.TRUE);
		
		setClassifications(classifications, false);
		setAckAfterHandle(false);
		super.handleRemaining(thrownException, records, consumer, container);
	}
	
	@Override
	public void handleRecord(Exception thrownException, ConsumerRecord<?, ?> record, Consumer<?, ?> consumer,
			MessageListenerContainer container) {
		System.out.println("!!!!!!!!!Inside Record!!!!!!!!!!!!!!!");
		super.handleRecord(thrownException, record, consumer, container);
	}
	
	@Override
	public void handleBatch(Exception thrownException, ConsumerRecords<?, ?> data, Consumer<?, ?> consumer,
			MessageListenerContainer container, Runnable invokeListener) {
		BackOff backOff=new FixedBackOff(1000L, 3L);
		BiFunction<ConsumerRecord<?, ?>, Exception, BackOff> backOffFunction=(a,b)->backOff;
		//setBackOffFunction(backOffFunction);
		System.out.println("!!!!!!!!!Inside Batch!!!!!!!!!!!!!!!");
		super.setBackOffFunction(backOffFunction);
		super.handleBatch(thrownException, data, consumer, container, invokeListener);
	}
	
}