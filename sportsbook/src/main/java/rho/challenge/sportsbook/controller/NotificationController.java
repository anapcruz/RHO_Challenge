package rho.challenge.sportsbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.service.INotificationService;

/**
 * Class controller notification
 * It is responsible for forwarding the received bet and returning the notification when the stake amount is greater or equals 100.
 */

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
