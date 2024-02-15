const express = require("express");
const cors = require("cors");
const http = require("http");
const socketIo = require("socket.io");

const app = express();
app.use(express.static("public"));
app.use(express.json());
app.use(cors());

const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: "http://127.0.0.1:5500",
    },
});

 
io.on("connection", (socket) => {
    console.log("New connection");
    socket.on("message", (payload) => {
        console.log(`Message received on server: ${payload}`);
        io.except(socket.id).emit("new-message", payload);
    });
});

server.listen(3000, function(err){
    if (err) console.log("Error in server setup")
    console.log("Server listening on Port", 3000);
})