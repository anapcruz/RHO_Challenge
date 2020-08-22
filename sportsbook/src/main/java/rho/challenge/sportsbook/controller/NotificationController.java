package rho.challenge.sportsbook.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.model.Player;

@Controller
public class NotificationController {

    @MessageMapping("/sportsBook.register")
    @SendTo("/topic/public")
    public Player createuser(@Payload Player player, SimpMessageHeaderAccessor headerAccessor) {
        headerAccessor.getSessionAttributes().put("username", player.getPlayerID());
        return player;
    }

}
