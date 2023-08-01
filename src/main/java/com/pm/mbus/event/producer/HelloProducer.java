package com.pm.mbus.event.producer;

import com.pm.mbus.config.KafkaProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HelloProducer {
    @Autowired
    @Qualifier(KafkaProducerConfig.KAFKA_PRODUCER_CONFIG_TEMPLATE_DEFAULT)
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sayHi() {
       kafkaTemplate.send("greeting", "HELLO " + new Date());
    }
}
