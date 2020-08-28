
var stompClient = null;

function setConnected(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connect() {
    var socket = new SockJS('/sportsBook');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/public', function(notification) {
            showNotification(JSON.parse(notification.body));
        });
    });
}

function disconnect() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendMessage() {
    var userID = document.getElementById('userID').value;
    var stake = document.getElementById('stake').value;
    stompClient.send("/app/sportsBook/register", {},
        JSON.stringify({'playerID':userID, 'stake':stake}));
}

function showNotification(notification) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode("{accountID: " + notification.playerID + ", stake:  " + notification.accumulatedAmount + "}"));
    response.appendChild(p);
}
