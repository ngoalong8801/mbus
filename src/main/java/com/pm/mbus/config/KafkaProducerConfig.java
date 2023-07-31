package com.pm.mbus.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {
    private final Map<String, Object> baseConfig;
    public static final String KAFKA_PRODUCER_CONFIG_TEMPLATE_DEFAULT = "kafkaTemplate";

    public static final String KAFKA_PRODUCER_CONFIG_TEMPLATE_OBJ = "kafkaTemplateObj";
    @Autowired
    public KafkaProducerConfig(@Value(value = "${spring.kafka.bootstrap-servers}") final String bootstrapAddress) {
        this.baseConfig = new HashMap<>();
        baseConfig.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        baseConfig.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = getBaseProducerFactoryConfig();
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private Map<String, Object> getBaseProducerFactoryConfig() {
       return new HashMap<>(baseConfig);
    }

    @Bean
    public ProducerFactory<String, Object> producerFactoryObj() {
        Map<String, Object> configProps = getBaseProducerFactoryConfig();
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean(KAFKA_PRODUCER_CONFIG_TEMPLATE_DEFAULT)
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean(KAFKA_PRODUCER_CONFIG_TEMPLATE_OBJ)
    public KafkaTemplate<String, Object> kafkaTemplateObj() {
        return new KafkaTemplate<>(producerFactoryObj());
    }
}
