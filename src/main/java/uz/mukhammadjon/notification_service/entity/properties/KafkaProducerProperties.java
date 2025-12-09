package uz.mukhammadjon.notification_service.entity.properties;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.kafka.producer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KafkaProducerProperties {

    String bootstrapServers;
    Class<?> keySerializer = StringSerializer.class;
    Class<?> valueSerializer = JsonSerializer.class;
}