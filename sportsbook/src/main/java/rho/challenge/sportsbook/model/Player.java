package rho.challenge.sportsbook.model;

import java.time.Instant;

/**
 * Auxiliary class
 * Class Player presents the information about a certain player:
 * 1. ID of the player
 * 2. information about if he is connected or disconnected
 */
public class Player {

    /**
     * enumerate of the messages types
     */
    public enum MessageType {
        LEAVE, JOIN
    }

    /**
     * Type of messages
     */
    private MessageType type;

    /**
     * ID of the player
     */
    private Long playerID;

    /**
     * Instant of time of the bet.
     */
    private Long time =  Instant.now().getEpochSecond();

    /**
     * Bet amount
     */
    private Double stake;


    /**
     * Default constructor
     */
    public Player() {
    }

    public Player(Long playerID, Long time) {
        this.playerID = playerID;
        this.time = time;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public Long getTime() {
        return time;
    }

    public Double getStake() {
        return stake;
    }
}
