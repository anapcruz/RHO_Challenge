package rho.challenge.sportsbook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.repository.NotificationRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class NotificationServiceUnitTest {

    @InjectMocks
    NotificationService notificationService;

    @Mock
    NotificationRepository notificationRepository;

    @Test
    void processBet() {
        Bet bet = new Bet(1, 30);
        Notification notification  = notificationService.processBet(bet);

        assertEquals(null,  notification);
    }

    @Test
    void processNotification(){
        Bet bet = new Bet(2, 100);
        Notification n =  new Notification(2, 100);

        Mockito.when(notificationRepository.save(n)).thenReturn(n);
        Notification notification = notificationService.processBet(bet);

        assertEquals(n, notification);

    }
}