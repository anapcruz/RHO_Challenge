'use strict';

var playerPage = document.querySelector('#player-page');
var playerForm = document.querySelector('#playerForm');
var betPage = document.querySelector('#bet-page');

var stompClient = null;
var username = null;


function connect(event){
    username = document.querySelector('#name').value.trim();

    if(username){
        playerPage.classList.add('hidden');
        betPage.classList.remove('hidden');
        var socket = new SockJS('/sports-book');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected, onError);
    }

    event.preventDefault();
}

function onConnected(){

    //stompClient.subscribe('/topic/public', onBetReceived);

    stompClient.send('/app/sportsBook.register'), {},JSON.stringify({playerID: username, type:'JOIN'})

    connectingElement.classList.add('hidden');
}


function onError(error){
    connectingElement.textContent = 'Could not connect to WebSocket server.';
    connectingElement.style.color = 'red';
}

//buttons
playerForm.addEventListener('submit', connect, true)