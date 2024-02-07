package com.realEstate.realEstate.config.kafka;


import com.google.common.collect.ImmutableMap;
import com.realEstate.realEstate.model.dto.chatting.AggregationDto;
import com.realEstate.realEstate.model.dto.chatting.Message;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Map;

@EnableKafka
@Configuration
public class ProducerConfiguration {

    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }

    // Kafka Producer 구성을 위한 설정값들을 포함한 맵을 반환하는 메서드
    @Bean
    public Map<String, Object> producerConfigurations() {
        return ImmutableMap.<String, Object>builder()
                .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092")
                .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
                .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class)
                .build();
    }

    @Bean
    public ProducerFactory<String, AggregationDto> aggregationProducerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigurations());
    }


    @Bean
    public KafkaTemplate<String, Message> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());

    }

    @Bean
    public KafkaTemplate<String, AggregationDto> aggregationDtoKafkaTemplate() {
        return new KafkaTemplate<>(aggregationProducerFactory());
    }
}
