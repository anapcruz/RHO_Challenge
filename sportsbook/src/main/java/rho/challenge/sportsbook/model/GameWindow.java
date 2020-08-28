package rho.challenge.sportsbook.model;

import java.util.LinkedList;

/**
 * Auxiliary Class GameWindow
 * This class is responsible to store all the bets received in the current bet window
 */
public class GameWindow {

    /**
     * Bet window
     */
    private static final int timeWindow = 60;

    /**
     * Total bet amount in the current bet window
     */
    private double totalBet = 0;

    /**
     * List of all bets
     */
    private LinkedList<Bet> betList = new LinkedList<>();


    /**
     * Returns the total bet amount in the current bet window
     * @return total bet amount
     */
    public double getTotalBet() {
        return totalBet;
    }

    /**
     * Adds the received bet to the list and increase the total bet amount to the local variable "totalBet".
     * Compares the difference of time between the last bet added and the first bet added and the current time window
     * in case of true, that means the bet is old so it will be removed.
     *
     * With this function, we have all the bets within the current time window.
     * @param bet received bet.
     */
    public void addBet (Bet bet){
        this.betList.add(bet);
        totalBet += bet.getStake();

        while (betList.getLast().getTime().getEpochSecond() - betList.getFirst().getTime().getEpochSecond() > timeWindow){
            totalBet -= betList.getFirst().getStake();
            betList.removeFirst();
        }
    }
}
