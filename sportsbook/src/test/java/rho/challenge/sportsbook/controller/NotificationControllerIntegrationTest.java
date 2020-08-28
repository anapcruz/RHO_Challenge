package rho.challenge.sportsbook.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import rho.challenge.sportsbook.SportsbookApplication;
import rho.challenge.sportsbook.model.Notification;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.SECONDS;
import static junit.framework.TestCase.assertEquals;

/**
 * Integration tests
 * Addapted from https://rieckpil.de/write-integration-tests-for-your-spring-websocket-endpoints/
 */

@ActiveProfiles("test")
@SpringBootTest(classes = SportsbookApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class NotificationControllerIntegrationTest {
    /**
     * For a client receives a notification he need to subscribe the topic "/topic/public/"
     * For a client sends a bet, he sends it to the topic "app/sportsBook/register"
     */
    static final String WEB_SOCKET_URI = "ws://localhost:8080/sportsBook";
    static final String WEB_TOPIC = "/topic/public";
    static final String WEB_TOPIC_SEND = "/app/sportsBook/register";

    BlockingQueue<String> blockingQueue;
    WebSocketStompClient stompClient;


    @BeforeEach
    void setup(){
        blockingQueue = new LinkedBlockingDeque<>();
        this.stompClient = new WebSocketStompClient(new SockJsClient(
                Arrays.asList(new WebSocketTransport(new StandardWebSocketClient()))));
    }

    @Test
    void testReceivedBet() throws InterruptedException, ExecutionException, TimeoutException {

        StompSession session = stompClient
                .connect(WEB_SOCKET_URI, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);

        session.subscribe(WEB_TOPIC, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                blockingQueue.add(new String ((byte[]) o));
            }
        });

        String sendBet = "{\"playerID\":\"1\",\"stake\":\"40\"}";
        session.send(WEB_TOPIC_SEND, sendBet.getBytes());
        assertEquals(null, blockingQueue.poll(1, TimeUnit.SECONDS));

    }

    @Test
    void testSendNotification() throws InterruptedException, ExecutionException, TimeoutException {

        StompSession session = stompClient
                .connect(WEB_SOCKET_URI, new StompSessionHandlerAdapter() {})
                .get(1, SECONDS);

        session.subscribe(WEB_TOPIC, new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders stompHeaders) {
                return byte[].class;
            }

            @Override
            public void handleFrame(StompHeaders stompHeaders, Object o) {
                blockingQueue.add(new String ((byte[]) o));
            }
        });

        String sendBet = "{\"playerID\":\"12\",\"stake\":\"100.0\"}";
        session.send(WEB_TOPIC_SEND, sendBet.getBytes());

        Notification sendNotification = new Notification(12, 100.0);
        String expected = sendNotification.toJson();

        String actual = blockingQueue.poll(1, SECONDS);

        assertEquals(expected, actual);
    }

    
}