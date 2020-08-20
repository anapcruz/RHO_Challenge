package rho.challenge.sportsbook.model;

import lombok.Data;

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
    private Integer playerID;

    /**
     * Accumulated amount of the bets in the current bet window
     */
    private Double accumulatedAmount;

    /**
     *
     * @param playerID ID of the player
     * @param accumulatedAmount Total bet amount of the player in the current window
     */
    public Notification(int playerID, double accumulatedAmount) {
        this.playerID = playerID;
        this.accumulatedAmount = accumulatedAmount;
    }

    /**
     * Default constructor
     */
    public Notification() {

    }

    /**
     *
     * @return
     */
    public int getPlayerID() {
        return playerID;
    }

    /**
     *
     * @param playerID
     */
    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    /**
     *
     * @return
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Notification{" +
                "playerID=" + playerID +
                ", accumulatedAmount=" + accumulatedAmount +
                '}';
    }
}