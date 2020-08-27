package rho.challenge.sportsbook.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotificationTest {

    @Test
    public void testInvalidAccountID(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Notification( -2,  22.2);
        });
        assertTrue(exception.getMessage().contains("Player ID must be positive integer"));
    }

    @Test
    public void testAccumlatedAmmoutOutOfBond(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Notification( 1,  22.2);
        });
        assertTrue(exception.getMessage().contains("Accumulated amount must be greater or equals to 100"));

        Exception exception_neg = assertThrows(IllegalArgumentException.class, ()-> {
            new Notification( 1,  -20);
        });
        assertTrue(exception_neg.getMessage().contains("Accumulated amount must be positive and greater or equals to 100"));
    }
}