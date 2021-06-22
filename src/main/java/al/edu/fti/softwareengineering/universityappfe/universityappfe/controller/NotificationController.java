package al.edu.fti.softwareengineering.universityappfe.universityappfe.controller;

import al.edu.fti.softwareengineering.universityappfe.universityappfe.apiConsumers.service.NotificationService;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.models.Notification;
import al.edu.fti.softwareengineering.universityappfe.universityappfe.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private MessageUtil messageUtil;

    @GetMapping
    public String unseenNotifications(Model model) {
        List<Notification> notifications = Arrays.asList(this.notificationService.getNotificationsOfCurrentUser().getBody());
        notifications.forEach(notification -> notification.setContent(this.messageUtil.getMessageWithParams(notification.getContent(), new Object[] {notification.getParameters()})));
        model.addAttribute("notifications", notifications);
        notificationService.seeAllNotifications(notifications);
        return "notification/unseen";
    }

    private Object[] getParametersOfANotification(String parameters) {
        return parameters.split("|");
    }
}
