const express = require("express");
const cors = require("cors");
const http = require("http");
const socketIo = require("socket.io");
const fs = require("fs");
const { connect } = require("http2");

const app = express();
app.use(express.static("public"));
app.use(express.json());
app.use(cors());

const server = http.createServer(app);
const io = new socketIo.Server(server, {
    cors: {
        origin: "*",
    },
});
let mapping = {};
io.on("connection", async (socket) => {
    console.log("New connection");
    socket.on("join", (payload) => {
        if (mapping[socket.id] != null) socket.leave(mapping[socket.id]);
        socket.join(payload);

        console.log(payload);
        mapping[socket.id] = payload;
    });
    socket.on("new-trade", (payload) => {
        console.log(mapping[socket.id]);
        io.to(mapping[socket.id]).except(socket.id).emit("new-trade", payload);
    });
});
server.listen(3000, function (err) {
    if (err) console.log("Error in server setup");
    console.log("Server listening on Port", 3000);
});
