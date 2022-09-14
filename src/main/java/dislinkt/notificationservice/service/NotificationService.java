package dislinkt.notificationservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dislinkt.notificationservice.entities.Notification;
import dislinkt.notificationservice.entities.NotificationType;
import dislinkt.notificationservice.kafka.KafkaNotification;
import dislinkt.notificationservice.kafka.KafkaNotificationType;
import dislinkt.notificationservice.kafka.KafkaPayload;
import dislinkt.notificationservice.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository repository;

    public Notification save(KafkaNotification kafkaObj) {
        HashMap<String, Object> payload = (HashMap<String, Object>) kafkaObj.getPayload();
        Notification notification = new Notification();
        notification.setSenderId((Integer) payload.get("senderId"));
        notification.setRecipientId((Integer) payload.get("recipientId"));
        notification.setTimestamp((Long) payload.get("timestamp"));
        NotificationType type = getNotificationType(kafkaObj.getType());
        notification.setNotificationType(type);
        notification = repository.save(notification);
        return notification;
    }

    public List<Notification> getAllNotifications(int recipientId) {
        return repository.findAllByRecipientIdOrderByTimestampDesc(recipientId);
    }

    private NotificationType getNotificationType(KafkaNotificationType event) {
        if (event.name().equalsIgnoreCase("MESSAGE_SENT")) {
            return NotificationType.MESSAGE_SENT;
        } else if (event.name().equalsIgnoreCase("CONNECTION_REQUEST")) {
            return NotificationType.CONNECTION_REQUEST;
        } else if (event.name().equalsIgnoreCase("NEW_POST")) {
            return NotificationType.NEW_POST;
        } else if (event.name().equalsIgnoreCase("LIKE")) {
            return NotificationType.LIKE;
        }
        return null;
    }

}
