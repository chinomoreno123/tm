package pl.poleng.config;

import java.util.Arrays;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import pl.poleng.messaging.MessageReceiver;

@Configuration
@PropertySource("classpath:jms.properties")
public class MessageConfig {

	@Value("${default_broker_url}")
	private String DEFAULT_BROKER_URL;

	@Value("${order_queue}")
	private String ORDER_QUEUE = "order-queue";

	@Value("${order_queue_response}")
	private String ORDER_RESPONSE_QUEUE = "order-response-queue";

	@Autowired
	MessageReceiver messageReceiver;

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
		connectionFactory.setTrustedPackages(Arrays.asList("java.lang", "java.util", "pl.poleng"));
		RedeliveryPolicy policy = connectionFactory.getRedeliveryPolicy();
		policy.setMaximumRedeliveries(4);
		policy.setRedeliveryDelay(10000);
		connectionFactory.setRedeliveryPolicy(policy);
		return connectionFactory;
	}
	/*
	 * Optionally you can use cached connection factory if performance is a big
	 * concern.
	 */

	@Bean
	public ConnectionFactory cachingConnectionFactory() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
		connectionFactory.setTargetConnectionFactory(connectionFactory());
		connectionFactory.setSessionCacheSize(10);
		return connectionFactory;
	}

	/*
	 * Message listener container, used for invoking messageReceiver.onMessage
	 * on message reception.
	 */
	@Bean
	public MessageListenerContainer getContainer() {
		DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
		container.setConnectionFactory(connectionFactory());
		container.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		container.setDestinationName(ORDER_QUEUE);
		//container.setMessageListener(messageReceiver);

		return container;
	}

	/*
	 * Used for Sending Messages.
	 */
	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(connectionFactory());
		template.setDefaultDestinationName(ORDER_QUEUE);
		return template;
	}

	@Bean
	MessageConverter converter() {
		return new SimpleMessageConverter();
	}

}