package rho.challenge.sportsbook.model;

import java.time.Instant;

/**
 * Auxiliary class
 * Class Bet presents the information about a certain game:
 * 1. ID of the player
 * 2. the stake amount
 * 3. the time of the player does the bet
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
     *
     * @param playerID
     * @param stake
     */
    public Bet(long playerID, double stake) {
        this.playerID = playerID;
        this.stake = stake;
        this.time = Instant.now();
    }

    public long getPlayerID() {
        return playerID;
    }

    public Instant getTime() {
        return time;
    }

    public double getStake() {
        return stake;
    }
}
