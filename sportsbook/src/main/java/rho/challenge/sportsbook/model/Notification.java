package rho.challenge.sportsbook.model;

import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Data;
import net.minidev.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class
 */
@Data
@Entity
@Table(name = "NOTIFICATION")
public class Notification {

    /**
     * primary key to identify the notifications
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long notificationID;

    /**
     * ID of the player
     */
    private Long playerID;

    /**
     * Accumulate amount of the bets in the current bet window
     */
    private Double accumulateAmount;

    /**
     *
     * @param playerID
     * @param accumulateAmount
     */
    public Notification(Long playerID, Double accumulateAmount) {
        this.playerID = playerID;
        this.accumulateAmount = accumulateAmount;
    }


    /**
     * Default constructor
     */
    public Notification() {

    }

    /**
     * Returns the player ID
     * @return ID of the player
     */
    public Long getPlayerID() {
        return playerID;
    }

    /**
     * Returns the accumulate amount of the user in the current window
     * @return accumulate bet amount
     */
    public double getAccumulateAmount() {
        return accumulateAmount;
    }

    /**
     * 
     * @param accumulateAmount
     */
    public void setAccumulateAmount(double accumulateAmount) {
        this.accumulateAmount = accumulateAmount;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "playerID=" + playerID +
                ", accumulateAmount=" + accumulateAmount +
                '}';
    }

    public String Json(){
        JSONObject json = new JSONObject();
        json.put("player ID: ", this.playerID);
        json.put("stake:", this.accumulateAmount);
        return json.toString();
    }
}