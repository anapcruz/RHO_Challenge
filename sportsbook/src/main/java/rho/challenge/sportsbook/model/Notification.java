package rho.challenge.sportsbook.model;

import com.google.gson.JsonObject;
import lombok.Data;
import net.minidev.json.JSONObject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class Notification
 * It is responsible for saving all notifications for a specific user in a particular bet window, and the total bet amount is greater or equals to the threshold.
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
    private long notificationID;

    /**
     * ID of the player
     */
    private long playerID;

    /**
     * Accumulated amount of the bets in the current bet window
     */
    private double accumulatedAmount;

    /**
     * Notification constructor
     * @param playerID ID of the player
     * @param accumulatedAmount accumulated amount of the bets
     */
    public Notification(long playerID, double accumulatedAmount) {
        if (playerID <= 0)
            throw new IllegalArgumentException("Player ID must be positive integer");
        if (accumulatedAmount <= 0)
            throw new IllegalArgumentException("Accumulated amount must be positive and greater or equals to 100");
        if (accumulatedAmount > 0 && accumulatedAmount < 100)
            throw new IllegalArgumentException("Accumulated amount must be greater or equals to 100");

        this.playerID = playerID;
        this.accumulatedAmount = accumulatedAmount;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "playerID=" + playerID +
                ", accumulateAmount=" + accumulatedAmount +
                '}';
    }

    /**
     * Auxiliary function used in tests
     * @return json string
     */
    public String toJson(){
        JsonObject json = new JsonObject();
        json.addProperty("notificationID", this.notificationID + 1);
        json.addProperty("playerID", this.playerID);
        json.addProperty("accumulatedAmount", this.accumulatedAmount);
        return json.toString();
    }
}