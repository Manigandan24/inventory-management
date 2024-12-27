package sl.ms.inventorymanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfig {
	private Consumer consumer;
	private Producer producer;
	
	public static class Consumer{
		private String bootstrapServer;
		private String topicName;
		private String groupId;
		private String offsetReset;
		
		public String getBootstrapServer() {
			return bootstrapServer;
		}

		public void setBootstrapServer(String bootstrapServer) {
			this.bootstrapServer = bootstrapServer;
		}

		public String getTopicName() {
			return topicName;
		}

		public void setTopicName(String topicName) {
			this.topicName = topicName;
		}
		
		public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
		public String getOffsetReset() {
			return offsetReset;
		}
		public void setOffsetReset(String offsetReset) {
			this.offsetReset = offsetReset;
		}
	}
	
	
	public static class Producer{
		private String bootstrapServer;
		private String topicName;
		private String groupId;
		private String offsetReset;
		
		public String getBootstrapServer() {
			return bootstrapServer;
		}
		public void setBootstrapServer(String bootstrapServer) {
			this.bootstrapServer = bootstrapServer;
		}
		public String getTopicName() {
			return topicName;
		}
		public void setTopicName(String topicName) {
			this.topicName = topicName;
		}
		public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
		public String getOffsetReset() {
			return offsetReset;
		}
		public void setOffsetReset(String offsetReset) {
			this.offsetReset = offsetReset;
		}
		
	}


	public Consumer getConsumer() {
		return consumer;
	}


	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}


	public Producer getProducer() {
		return producer;
	}


	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
}
