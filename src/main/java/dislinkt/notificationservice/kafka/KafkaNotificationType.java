package dislinkt.notificationservice.kafka;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public enum KafkaNotificationType implements Serializable {
    CONNECTION_REQUEST(0),
    MESSAGE_SENT(1),
    NEW_POST(2),
    LIKE(3);

    private int value;
    private static Map map = new HashMap<>();

    private KafkaNotificationType(int value) {
        this.value = value;
    }

    static {
        for (KafkaNotificationType skillType : KafkaNotificationType.values()) {
            map.put(skillType.value, skillType);
        }
    }

    public static KafkaNotificationType valueOfInt(int skillType) {
        return (KafkaNotificationType) map.get(skillType);
    }

    public int getValue() {
        return value;
    }
}
