package rho.challenge.sportsbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.GameWindow;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.repository.NotificationRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService implements INotificationService{
    @Autowired
    private NotificationRepository repository;

    /**
     * Maximum of the total bet amount for a player in the current bet window
     */
    private static final int threshold = 100;

    /**
     * Map all bets for each player
     */
    Map<Long, GameWindow> mapBets = new HashMap<>();

    /**
     * Function receives the current bet
     * @param bet current bet
     * @return if the bet amount exceeds the threshold return a new notification; else, returns null
     */
    public Notification processBet(Bet bet){

        //check if the ID of the player exists in the map
        if(!mapBets.containsKey(bet.getPlayerID()))
            mapBets.put(bet.getPlayerID(), new GameWindow());

        //associate the ID of the player on the game window
        GameWindow window = mapBets.get(bet.getPlayerID());
        //save on the game window the current bet
        window.addBet(bet);

        //check if the total bet amount is greater or equals than the threshold, in case of true, creates a new notification
        if(window.getTotalBet()>= threshold)
            return createNotification(bet.getPlayerID(), window.getTotalBet());
        return null;
    }

    /**
     * Function responsible to add the notification to the database
     * @param playerID ID of the player
     * @param totalAmount the total bet amount
     * @return new notification created
     */
    private Notification createNotification(long playerID, double totalAmount){
        Notification notification = new Notification(playerID, totalAmount);

        Notification storeNotification = repository.save(notification);
        return storeNotification;
    }
}
