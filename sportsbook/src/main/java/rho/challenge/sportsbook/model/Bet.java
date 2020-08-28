package rho.challenge.sportsbook.model;

import java.time.Instant;

/**
 * Auxiliary class
 * Class Bet presents the information about a particular bet:
 * 1. The ID of the player
 * 2. The stake amount
 * 3. The instant of time of the bet
 */
public class Bet {

    /**
     * ID of the player
     */
    private long playerID;

    /**
     * Instant of time of the bet.
     */
    private Instant time;

    /**
     * Bet amount
     */
    private double stake;

    /**
     * Bet constructor
     * @param playerID
     * @param stake
     */
    public Bet(long playerID, double stake) {
        if (playerID <=0)
            throw new IllegalArgumentException("Player ID must be positive integer");
        if (stake <= 0)
            throw new IllegalArgumentException("Bet amount must be a positive value");

        this.playerID = playerID;
        this.stake = stake;
        this.time = Instant.now();
    }

    /***
     * Returns the player ID related to a particular bet.
     * @return player ID
     */
    public long getPlayerID() {
        return playerID;
    }

    /**
     * Returns the instant of time when a bet is received.
     * @return time
     */
    public Instant getTime() {
        return time;
    }

    /**
     * Returns the bet amount related to a particular bet.
     * @return bet amount
     */
    public double getStake() {
        return stake;
    }
}
