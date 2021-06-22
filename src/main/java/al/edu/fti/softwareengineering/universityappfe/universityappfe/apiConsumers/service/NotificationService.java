package al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.Notification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificationService extends BaseService<Notification, Long> {
    ResponseEntity<Notification[]> getNotificationsOfCurrentUser();

    ResponseEntity<Void> seeAllNotifications(List<Notification> notifications);;
}
