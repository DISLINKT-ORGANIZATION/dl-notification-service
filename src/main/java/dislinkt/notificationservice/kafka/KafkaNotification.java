package dislinkt.notificationservice.kafka;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = KafkaNotification.class)
public class KafkaNotification {

    private static final long serialVersionUID = 1L;

    private Object payload;

    private KafkaNotificationType type;

    @Override
    public String toString() {
        return "Notification [payload=" + payload + ", type=" + type + "]";
    }
}
