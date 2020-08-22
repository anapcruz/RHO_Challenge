package rho.challenge.sportsbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rho.challenge.sportsbook.Constants;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.repository.NotificationRepository;

import java.util.*;

@Service
public class NotificationService implements INotificationService{
    @Autowired
    private NotificationRepository repository;

    /**
     * Map all bets for each player
     */
    Map<Long, List<Bet>> map = new HashMap<>();

    public void createBet(Bet bet) {

        //check if the player has any bet created
        if(!map.containsKey(bet.getPlayerID())){

            Bet newbet =  new Bet(bet.getStake(), bet.getTime());
            ArrayList<Bet> allBets = new ArrayList<>();
            allBets.add(newbet);
            map.put(bet.getPlayerID(), allBets);
        }
        else {
            //the player has previous bet, so it is necessary:
            // 1. check if the stakes does not higher than the threshold in a certain time window

            long startWindow =  bet.getTime() - Constants.TIME_WINDOW;

            // check
        }
    }

    /**
     * Create and store notification into database
     * @param playerID ID of the player
     * @param accumlatedAmount bet amount in the current window
     * @return the created notification
     */
    private Notification createNotification(Long playerID, double accumlatedAmount) throws Exception {
        Notification notification = new Notification(playerID, accumlatedAmount);

        try{
            Notification storeNotification = repository.save(notification);
            return storeNotification;
        }catch (Exception e){
            throw new Exception("Failed to store the notification in the database!");
        }
    }
}
