package com.pm.mbus.event.consumer;

import com.pm.mbus.config.KafkaConsumerConfig;
import com.pm.mbus.dto.payload.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PersonConsumer {
    private static final Logger LOG = LogManager.getFormatterLogger(PersonConsumer.class);

    @KafkaListener(
            topics = "person",
            groupId = "foo",
            containerFactory = KafkaConsumerConfig.KAFKA_CONSUMER_CONFIG_CONTAINER_FACTORY_OBJ)
    public void helloPerson(Person person) {
        LOG.info(person);
    }
}

