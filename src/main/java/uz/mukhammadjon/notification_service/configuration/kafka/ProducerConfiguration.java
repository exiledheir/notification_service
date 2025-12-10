package uz.mukhammadjon.notification_service.configuration.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonSerializer;
import uz.mukhammadjon.notification_service.configuration.props.KafkaProps;
import uz.mukhammadjon.notification_service.dto.notification.event.NotificationEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProducerConfiguration {

    KafkaProps kafkaProps;
    ObjectMapper objectMapper;

    private Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProps.getClientId() + UUID.randomUUID());
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapServers());
        props.put(ProducerConfig.CLIENT_DNS_LOOKUP_CONFIG, kafkaProps.getClientDnsLookup());
        props.put(ProducerConfig.ACKS_CONFIG, kafkaProps.getAcksConfig());
        props.put(ProducerConfig.RETRIES_CONFIG, kafkaProps.getRetriesConfig());
        props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaProps.getBatchSizeConfig());
        props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaProps.getLingerMsConfig());
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaProps.getBufferMemoryConfig());

        return props;
    }

    @SuppressWarnings("removal")
    @Bean
    public KafkaTemplate<String, NotificationEvent> notificationKafkaTemplate() {
        JsonSerializer<NotificationEvent> serializer = new JsonSerializer<>(objectMapper);
        serializer.setAddTypeInfo(false);

        DefaultKafkaProducerFactory<String, NotificationEvent> factory = new DefaultKafkaProducerFactory<>(
            producerConfigs(),
            new StringSerializer(),
            serializer
        );
        return new KafkaTemplate<>(factory);
    }
}