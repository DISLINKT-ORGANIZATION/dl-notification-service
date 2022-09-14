package dislinkt.notificationservice.controller;

import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import dislinkt.notificationservice.service.SocketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationController {

    private static final Logger log = LoggerFactory.getLogger(NotificationController.class);

    private final SocketIONamespace namespace;

    @Autowired
    public NotificationController(SocketIOServer server, SocketService socketService) {
        this.namespace = server.addNamespace("/notification");
        this.namespace.addConnectListener(onConnected());
        this.namespace.addDisconnectListener(onDisconnected());
    }


    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
        };
    }

    private DisconnectListener onDisconnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.leaveRoom(room);
        };
    }

}
