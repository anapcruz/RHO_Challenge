'use strict';

var playerPage = document.querySelector('#game-page');
var playerForm = document.querySelector('#playerForm');
var betPage = document.querySelector('#chat-page');
var betForm = document.getElementById('sendMessage');

var connectingElement = document.querySelector('.connecting');

var stompClient = null;
let username;
var stake  = null;

function connect(event){
    username = document.querySelector('#name').value.trim();

    if(username){
        playerPage.classList.add('hidden');
        betPage.classList.remove('hidden');

        var socket = new SockJS('/sportsBook');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame){
            console.log("olaaaaaaaaaa " + frame);
            onConnected();
        });
    }

    event.preventDefault();
}

function onConnected(){

    var response = document.getElementById('name-header');
    var header = document.createElement('h2');
    header.appendChild(document.createTextNode("Welcome player " + username ));
    response.appendChild(header);

    stompClient.subscribe('/topic/public', function (noti){
        showmessage(JSON.parse(noti.body).content);
        console.log("aquiiiii " + noti.body);
    });


    //stompClient.send('/app/sportsBook.register'), {},JSON.stringify({playerID: username, type:'JOIN'})
    //connectingElement.classList.add('hidden');
}

function sendBet(){
    stake = document.querySelector('#bet').value.trim();
    console.log(stake);
    console.log(username);
    stompClient.send('/app/sportsBook/register'), {},JSON.stringify({playerID: username, stake:stake});
}

function showmessage(noti){

    var response = document.getElementById('notifications');
    var not_id =  document.createElement('ul');
    not_id.appendChild(document.createTextNode(noti));
    response.appendChild(not_id);
    console.log(noti);

}

function onError(error){
    connectingElement.textContent = 'Could not connect to WebSocket server.';
    connectingElement.style.color = 'red';
}

//buttons
playerForm.addEventListener('submit', connect)
$(function (){
   $('#sendMessage').click(function (){sendBet(); });
});
//betForm.addEventListener('submit', sendBet)