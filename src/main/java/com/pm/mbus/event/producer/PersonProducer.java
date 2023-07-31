package com.pm.mbus.event.producer;

import com.pm.mbus.config.KafkaProducerConfig;
import com.pm.mbus.dto.payload.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class PersonProducer {


    @Autowired
    @Qualifier(KafkaProducerConfig.KAFKA_PRODUCER_CONFIG_TEMPLATE_OBJ)
    private KafkaTemplate<String, Object> kafkaTemplateObj;

    public void hiPerson(Person person) {
        kafkaTemplateObj.send("person", person);
    }
}
