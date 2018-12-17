package application.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import application.config.RabbitMQConfig;
import application.domain.Estoque;

@Component
public class CompraListener {

	static final Logger logger = LoggerFactory.getLogger(CompraListener.class);

    @RabbitListener(queues = RabbitMQConfig.COMPRA_FILA)
    public void processMessage(Estoque message) {
        logger.info("Message Received - BUY");
        logger.info("EnterpriseId:" + message.getId());
    }	
	
}
