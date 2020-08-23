package rho.challenge.sportsbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rho.challenge.sportsbook.Constants;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.model.Player;
import rho.challenge.sportsbook.repository.NotificationRepository;

import java.util.*;

@Service
public class NotificationService implements INotificationService{
    @Autowired
    private NotificationRepository repository;

    /**
     * Map all bets for each player
     */
    Map<Long, List<Bet>> mapBets = new HashMap<>();

    public String checkPlayerBet(Player player) throws Exception {

        //check if is the first bet for a certain player
        if(!mapBets.containsKey(player.getPlayerID())){

            if(player.getStake()>= Constants.THRESHOLD){
                return createNotification(player.getPlayerID(), player.getStake()).toString();
            }
            Bet newbet =  new Bet(player.getStake(), player.getTime());
            ArrayList<Bet> allBets = new ArrayList<>();
            allBets.add(newbet);
            mapBets.put(player.getPlayerID(), allBets);
        }
        else {
            //the player has previous bet, so it is necessary:
            // 1. check if the stakes does not higher than the threshold in a certain time window

            //returns the total bet amounts until the current window
            double accumulatedStake = checkBets(player.getPlayerID(), player.getTime());

            accumulatedStake += player.getStake();

            if (accumulatedStake >= Constants.THRESHOLD){
                return createNotification(player.getPlayerID(), accumulatedStake).toString();
            }
            else{
                Bet newbet =  new Bet(player.getStake(), player.getTime());
                ArrayList<Bet> allBets = new ArrayList<>();
                allBets.add(newbet);
                mapBets.put(player.getPlayerID(), allBets);
            }
        }
        return null;
    }

    /**
     * Auxiliary function
     * @param playerID
     * @param currentwindow
     * @return
     */
    private double checkBets (long playerID, long currentwindow){
        //variable returns the total bet amount related to the player
        double totalBet = 0;

        // array list with all bets related to playerID;
        ArrayList<Bet> betlist = (ArrayList<Bet>) mapBets.get(playerID);

        //calculate the instant of time that the window starts
        long startWindow = currentwindow - Constants.TIME_WINDOW;

        Iterator<Bet> bet = betlist.iterator();

        //iterator all the bets for this player
        while (bet.hasNext()){
            Bet currentBet = bet.next();
            double stake = currentBet.getStake();
            long time = currentBet.getTime();

            if(startWindow >= time){
                //if the bet is out of the window, it should be discarded
                System.out.println("Bet related to player: " + playerID + "was discarded!");
                bet.remove();
            }else{
                //increment all the bets in the current window
                totalBet += stake;
            }
        }
        return totalBet;
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
