const websocket_package = require("ws");
const websocket_portnumber = 4001;
const serverSocket = new websocket_package.Server({ port: websocket_portnumber });

console.log("opening a webscoket on port " + websocket_portnumber);

serverSocket.on('connection', (socket) => {
    console.log("connection accepted");
    socket.onmessage = function(event) {
        console.log("message received");
        console.log("\t" + event.data);
    };
});
