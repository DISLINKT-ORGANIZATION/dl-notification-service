package dislinkt.notificationservice.controller;

import dislinkt.notificationservice.entities.Notification;
import dislinkt.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationRestController {

    @Autowired
    private NotificationService notificationService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user/{recipientId}")
    public ResponseEntity<Object> getAllNotifications(@PathVariable int recipientId) {
        List<Notification> notifications = notificationService.getAllNotifications(recipientId);
        return ResponseEntity.ok(notifications);
    }

}
