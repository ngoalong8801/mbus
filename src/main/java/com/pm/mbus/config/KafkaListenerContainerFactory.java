package com.pm.mbus.config;

import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;

public class KafkaListenerContainerFactory {
    public static class Builder {
        private ConsumerFactory<? super String, ? super String> consumerFactory;

        public Builder() {
            // TODO document why this constructor is empty
        }

        public Builder withConsumerFactory(ConsumerFactory<? super String, ? super String> consumerFactory) {
            this.consumerFactory = consumerFactory;
            return this;
        }

        public ConcurrentKafkaListenerContainerFactory<String, String> build() {
            ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory);
            return factory;
        }
    }

    private KafkaListenerContainerFactory() {

    }
}
