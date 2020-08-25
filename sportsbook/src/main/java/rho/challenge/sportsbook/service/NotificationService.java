package rho.challenge.sportsbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.GameWindow;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.repository.NotificationRepository;

import java.util.*;

@Service
public class NotificationService implements INotificationService{
    @Autowired
    private NotificationRepository repository;
    private static final int threshold = 100;

    /**
     * Map all bets for each player
     */
    Map<Long, GameWindow> mapBets = new HashMap<>();

    public Notification processBet(Bet bet){

        if(!mapBets.containsKey(bet.getPlayerID()))
            mapBets.put(bet.getPlayerID(), new GameWindow());

        GameWindow window = mapBets.get(bet.getPlayerID());
        window.addBet(bet);

        if(window.getTotalBet()>= threshold)
            return createNotification(bet.getPlayerID(), window.getTotalBet());
        return null;
    }

    private Notification createNotification(long playerID, double totalAmount){
        Notification notification = new Notification(playerID, totalAmount);

        Notification storeNotification = repository.save(notification);
        return storeNotification;
    }
}
