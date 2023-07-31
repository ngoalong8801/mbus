package com.pm.mbus.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {
    private final Map<String, Object> baseConfig;
    public static final String KAFKA_CONSUMER_CONFIG_CONTAINER_FACTORY_DEFAULT = "kafkaListenerContainerFactory";

    public static final String KAFKA_CONSUMER_CONFIG_CONTAINER_FACTORY_OBJ = "kafkaListenerContainerFactoryObj";
    @Autowired
    public KafkaConsumerConfig(@Value(value = "${spring.kafka.bootstrap-servers}") final String bootstrapAddress) {
        this.baseConfig = new HashMap<>();
        baseConfig.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        baseConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> props = getBaseConsumerFactoryConfig();
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConsumerFactory<String, Object> objectConsumerFactory() {
        Map<String, Object> props = getBaseConsumerFactoryConfig();
        props.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                JsonDeserializer.class
        );
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        return new DefaultKafkaConsumerFactory<>(props);
    }

    private Map<String, Object> getBaseConsumerFactoryConfig() {
        return new HashMap<>(baseConfig);
    }

    @Bean(KAFKA_CONSUMER_CONFIG_CONTAINER_FACTORY_DEFAULT)
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactory() {
        return new KafkaListenerContainerFactory.Builder()
                .withConsumerFactory(consumerFactory())
                .build();
    }

    @Bean(KAFKA_CONSUMER_CONFIG_CONTAINER_FACTORY_OBJ)
    public ConcurrentKafkaListenerContainerFactory<String, String>
    kafkaListenerContainerFactoryObj() {
        return new KafkaListenerContainerFactory.Builder()
                .withConsumerFactory(objectConsumerFactory())
                .build();
    }
}