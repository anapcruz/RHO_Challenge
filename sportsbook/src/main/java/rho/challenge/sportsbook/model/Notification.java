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
    private long notificationID;

    /**
     * ID of the player
     */
    private long playerID;

    /**
     * Accumulate amount of the bets in the current bet window
     */
    private double accumulatedAmount;

    /**
     *
     * @param playerID
     * @param accumulatedAmount
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


    /**
     * Returns the player ID
     * @return ID of the player
     */
    public long getPlayerID() {
        return playerID;
    }

    /**
     * Returns the accumulate amount of the user in the current window
     * @return accumulate bet amount
     */
    public double getAccumulatedAmount() {
        return accumulatedAmount;
    }

    /**
     * 
     * @param accumulatedAmount
     */
    public void setAccumulatedAmount(double accumulatedAmount) {
        this.accumulatedAmount = accumulatedAmount;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "playerID=" + playerID +
                ", accumulateAmount=" + accumulatedAmount +
                '}';
    }

    public String toJson(){
        JsonObject json = new JsonObject();
        json.addProperty("notificationID", this.notificationID + 1);
        json.addProperty("playerID", this.playerID);
        json.addProperty("accumulatedAmount", this.accumulatedAmount);

        return json.toString();
    }
}