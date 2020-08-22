package rho.challenge.sportsbook.model;


import java.time.Instant;

public class Bet {

    /**
     * ID of the player
     */
    private Long playerID;

    /**
     * Bet amount
     */
    private Double stake;

    /**
     * Instant of time of the bet.
     */
    private Long time =  Instant.now().getEpochSecond();

    /**
     * Default constructor
     */
    public Bet() {
    }

    /**
     * Constructor assign to create a new bet
     * @param stake
     * @param time
     */
    public Bet(Double stake, Long time){
        this.stake = stake;
        this.time = time;
    }

    /**
     *
     * @param playerID
     * @param stake
     * @param time
     */
    public Bet(Long playerID, Double stake, Long time) {
        this.playerID = playerID;
        this.stake = stake;
        this.time = time;
    }

    public Long getPlayerID() {
        return playerID;
    }

    public Double getStake() {
        return stake;
    }

    public Long getTime() {
        return time;
    }

}
