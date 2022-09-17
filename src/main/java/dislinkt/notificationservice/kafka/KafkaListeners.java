package dislinkt.notificationservice.kafka;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import dislinkt.notificationservice.entities.Notification;
import dislinkt.notificationservice.service.NotificationService;
import dislinkt.notificationservice.service.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private static final Logger logger = LoggerFactory.getLogger(KafkaListeners.class);

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SocketService socketService;

    @Autowired
    private SocketIOServer server;

    @KafkaListener(topics = "dislinkt-user-notifications", groupId = "groupId")
    void listener(@Payload KafkaNotification data) {
        logger.info("Listener received " + data);

        Notification notification = notificationService.save(data);
        SocketIONamespace namespace = server.getNamespace("/notification");
        socketService.sendNotification(notification.getRecipientId() + "", namespace, notification);
        // log message type
        switch (data.getType()) {
            case MESSAGE_SENT:
                logger.info("[EVENT]: MESSAGE_SENT");
                break;
            case CONNECTION_REQUEST:
                logger.info("[EVENT]: CONNECTION_REQUEST");
                break;
            case NEW_POST:
                logger.info("[EVENT]: NEW_POST");
                break;
            case LIKE:
                logger.info("[EVENT]: LIKE EVENT");
                break;
            case COMMENT:
                logger.info("[EVENT]: COMMENT");
                break;
            case NEW_CONNECTION:
                logger.info("[EVENT]: NEW CONNECTION");
                break;
            default:
        }
    }
}
