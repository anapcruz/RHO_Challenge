package rho.challenge.sportsbook.service;


import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;

public interface INotificationService {
    Notification processBet(Bet bet);


}
