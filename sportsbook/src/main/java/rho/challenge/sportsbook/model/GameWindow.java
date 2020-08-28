package rho.challenge.sportsbook.model;

import java.util.LinkedList;

public class GameWindow {
    private static final int timeWindow = 60;

    private double totalBet = 0;
    private LinkedList<Bet> betList = new LinkedList<>();

    public double getTotalBet() {
        return totalBet;
    }

    public void addBet (Bet bet){
        this.betList.add(bet);
        totalBet += bet.getStake();

        while (betList.getLast().getTime().getEpochSecond() - betList.getFirst().getTime().getEpochSecond() > timeWindow){
            totalBet -= betList.getFirst().getStake();
            betList.removeFirst();
        }
    }
}
