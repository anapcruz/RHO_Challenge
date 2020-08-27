package rho.challenge.sportsbook.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BetTest {

    @Test
    public void testInvalidAccountID(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()-> {
            new Bet(0,  20);
        });
        assertTrue(exception.getMessage().contains("Player ID must be positive integer"));
    }

    @Test
    public void testInvalidStake(){
        Exception exception = assertThrows(IllegalArgumentException.class, ()->{
            new Bet(12, -4);
        });
        assertTrue(exception.getMessage().contains("Bet amount must be a positive value"));
    }

}