package rho.challenge.sportsbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rho.challenge.sportsbook.repository.NotificationRepository;

@Service
public class NotificationService implements INotificationService{
    @Autowired
    private NotificationRepository repository;

}
