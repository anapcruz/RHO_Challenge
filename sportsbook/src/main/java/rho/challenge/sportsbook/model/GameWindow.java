package rho.challenge.sportsbook.model;

import java.util.LinkedList;

public class GameWindow {
    private static final int timeWindow = 60;

    private double totalBet = 0;
    private LinkedList<Bet> betlist = new LinkedList<>();

    public double getTotalBet() {
        return totalBet;
    }

    public void addBet (Bet bet){
        this.betlist.add(bet);
        totalBet += bet.getStake();

        while (betlist.getLast().getTime().getEpochSecond() - betlist.getFirst().getTime().getEpochSecond() > timeWindow){
            totalBet -= betlist.getFirst().getStake();
            betlist.removeFirst();
        }
    }
}
