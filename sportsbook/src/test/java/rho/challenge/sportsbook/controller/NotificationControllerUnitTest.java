package rho.challenge.sportsbook.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;
import rho.challenge.sportsbook.model.Bet;
import rho.challenge.sportsbook.model.Notification;
import rho.challenge.sportsbook.service.NotificationService;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * Unit tests with mockito and compatibility with Junit4
 * https://www.baeldung.com/mockito-junit-5-extension
 */

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class NotificationControllerUnitTest {

    @Mock
    NotificationService notificationService;
    @InjectMocks
    NotificationController notificationController;

    @Test
    void testSendBet() {
        Bet sendBet =  new Bet(1, 40);

        Mockito.when(notificationService.processBet(sendBet)).thenReturn(null);
        Notification actual = notificationController.createBet(sendBet);

        assertEquals(null, actual);
    }

    @Test
    void testSendNotification(){
        Bet sendBet =  new Bet(1, 100);
        Notification newNot = new Notification(1,100);

        Mockito.when(notificationService.processBet(sendBet)).thenReturn(newNot);
        Notification actual = notificationController.createBet(sendBet);

        assertEquals(newNot, actual);
    }
}