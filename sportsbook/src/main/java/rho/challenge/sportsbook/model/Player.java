package rho.challenge.sportsbook.model;


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
     * bet amount
     */
    private Double stake;

    /**
     * Create new
     * @param playerID ID of the player
     * @param stake bet amount
     */
    public Player(Long playerID, Double stake) {
        this.playerID = playerID;
        this.stake = stake;
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

    public void setPlayerID(Long playerID) {
        this.playerID = playerID;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }
}
