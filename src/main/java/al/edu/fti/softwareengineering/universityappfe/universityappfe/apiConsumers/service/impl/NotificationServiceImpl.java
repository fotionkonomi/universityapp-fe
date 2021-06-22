package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.impl;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.NotificationService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.Notification;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl extends BaseServiceImpl<Notification, Long> implements NotificationService {
    public NotificationServiceImpl() {
        super(Notification.class, Notification[].class);
    }

    @Override
    public ResponseEntity<Notification[]> getNotificationsOfCurrentUser() {
        return restCaller.getExchange(endpoint.getNotificationsOfCurrentUser(), Notification[].class);
    }

    @Override
    public ResponseEntity<Void> seeAllNotifications(List<Notification> notifications) {
        if(notifications != null) {
            notifications.forEach(notification -> restCaller.putExchange(endpoint.getSeeNotification(), new HttpEntity<>(notification.getId()), Void.class));
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
