package dislinkt.notificationservice.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaPayload {

    private long id;
    private long senderId;
    private long recipientId;
    private long timestamp;
}
