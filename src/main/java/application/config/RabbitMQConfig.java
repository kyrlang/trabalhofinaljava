package application.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;

@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer  {

    public static final String VENDA_FILA = "stock-exchange-sell-queue";
    public static final String COMPRA_FILA = "stock-exchange-buy-queue";
    public static final String EXCHANGE_MESSAGES = "messages-exchange";
    public static final String QUEUE_DEAD_MESSAGES = "dead-messages-queue";
 
    @Bean
    Queue vendaFila() {
    	return QueueBuilder.durable(VENDA_FILA)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
                .withArgument("x-message-ttl", 60000) // Mensagem possui 1min de vida na fila original
                .build();
    }

    @Bean
    Queue compraFila() {
    	return QueueBuilder.durable(COMPRA_FILA)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_MESSAGES)
                .withArgument("x-message-ttl", 60000) // Mensagem possui 1min de vida na fila original
                .build();
    }
 
    @Bean
    Queue deadMessagesQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_MESSAGES).build();
    }
 
    @Bean
    Exchange messagesExchange() {
        return ExchangeBuilder.topicExchange(EXCHANGE_MESSAGES).build();
    }
 
    @Bean
    Binding vendaFilaBinding(TopicExchange messagesExchange) {
        return BindingBuilder.bind(vendaFila()).to(messagesExchange).with(vendaFila().getName());
    }

    @Bean
    Binding compraFilaBinding(TopicExchange messagesExchange) {
        return BindingBuilder.bind(compraFila()).to(messagesExchange).with(compraFila().getName());
    }
    
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }
 
    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    
    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar register) {
        register.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }
 
    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }
 
    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }	
	
	
}
