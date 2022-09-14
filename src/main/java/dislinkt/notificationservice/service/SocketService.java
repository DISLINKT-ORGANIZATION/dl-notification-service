package dislinkt.notificationservice.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIONamespace;
import dislinkt.notificationservice.entities.Notification;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SocketService {

    public void sendNotification(String room, SocketIONamespace namespace, Notification notification) {
        Collection<SocketIOClient> clients = namespace.getRoomOperations(room).getClients();
        for (SocketIOClient client : clients) {
            client.sendEvent("notification", notification);
        }
    }
}

