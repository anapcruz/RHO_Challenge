package rho.challenge.sportsbook.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.service.INotificationService;

@Controller
public class NotificationController {

    private final INotificationService notification;

    @Autowired
    public NotificationController(INotificationService notification){
        this.notification = notification;
    }

    @MessageMapping("/sportsBook/register")
    @SendTo("/topic/public")
    public Notification createBet(Bet bet) {
        return notification.processBet(bet);
    }

}
