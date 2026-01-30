function connect() {

	ws = new WebSocket('ws://localhost:8080/path1');

	ws.onmessage = function(data){
        console.log("message received from server: " + data.data);
	}
}

function disconnect() {
    if (ws != null) {
        ws.close();
        console.log("Disconnected gracefully");
    }
}

function sendMessage() {
    ws.send("hi " + Date.now());
}