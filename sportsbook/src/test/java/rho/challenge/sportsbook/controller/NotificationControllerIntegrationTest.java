package rho.challenge.sportsbook.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import rho.challenge.sportsbook.SportsbookApplication;

import java.util.Arrays;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootTest(classes = SportsbookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NotificationControllerIntegrationTest {

    static final String WEB_SOCKET_URI = "ws://localhost:8080/sportsBook";
    static final String WEB_TOPIC = "/topic";

    BlockingDeque<String> blockingDeque;
    WebSocketStompClient stompClient;

    @BeforeEach
    public void setup(){
        blockingDeque = new LinkedBlockingDeque<>();
        stompClient = new WebSocketStompClient(new SockJsClient(
                Arrays.asList(new WebSocketTransport(new StandardWebSocketClient()))));
    }

    @Test
    void testReceivedBet() throws InterruptedException, ExecutionException, TimeoutException {
        StompSession session = stompClient
                .connect(WEB_SOCKET_URI, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);
        //session.subscribe(WEB_TOPIC,  new DefaultStompFrameHandler());


    }

    
}