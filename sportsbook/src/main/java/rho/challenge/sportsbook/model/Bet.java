package rho.challenge.sportsbook.model;

/**
 * Auxiliary class to create a bet
 * A bet is created in an instant of the time with a certain amount
 */
public class Bet {

    /**
     * Bet amount
     */
    private Double stake;

    /**
     * Instant of time of the bet.
     */
    private Long time;

    /**
     * Constructor assign to create a new bet
     * @param stake
     * @param time
     */
    public Bet(Double stake, Long time){
        this.stake = stake;
        this.time = time;
    }


    public Double getStake() {
        return stake;
    }

    public Long getTime() {
        return time;
    }

}
