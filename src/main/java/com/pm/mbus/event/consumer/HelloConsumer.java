package com.pm.mbus.event.consumer;

import com.pm.mbus.config.KafkaConsumerConfig;
import com.pm.mbus.dto.payload.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class HelloConsumer {
    private static final Logger LOG = LogManager.getFormatterLogger(HelloConsumer.class);
    @KafkaListener(
            topics = "greeting",
            groupId = "foo",
            containerFactory = KafkaConsumerConfig.KAFKA_CONSUMER_CONFIG_CONTAINER_FACTORY_DEFAULT)
    public void greetingListener(String message) {
        LOG.info(message);
    }
}
