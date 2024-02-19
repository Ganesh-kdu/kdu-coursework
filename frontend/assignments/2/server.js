const express = require("express");
const cors = require("cors");
const http = require("http");
const socketIo = require("socket.io");
const fs = require('fs');
const { connect } = require("http2");
const users = require("./users.json");

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

let started = false;
let connections = {}
let onlineUserDetails = [];
io.on("connection", async (socket) => {
    console.log("New connection");
    socket.on("register", (payload) => {
        console.log(payload);
        socket.emit("new-users",onlineUserDetails);
        connections[payload.user_name] = socket.id;
        onlineUserDetails.push(payload);
        io.except(socket.id).emit("new-user",payload);
        console.log(connections);
    })
    socket.conn.on("close",() => {
        const disconnectedUser = Object.keys(connections).find(key => connections[key] === socket.id);
        io.emit("remove-user", disconnectedUser);
        delete connections[disconnectedUser];
        const index = onlineUserDetails.findIndex(user => user.user_name === disconnectedUser);
        if (index !== -1) {
            onlineUserDetails.splice(index, 1);
        }
        console.log(connections);
    })
    socket.on("message",(payload) => {
    const { user, message } = payload;
    const toSocketId = connections[user];
    if (toSocketId) {
        io.to(toSocketId).emit("new-message", payload);
    } else {
        console.log(`User ${user} is not online.`);
    }
    console.log(payload);
    });
});


app.post("/api/user/login", (req, res) => {
    if (users[req.body.username]==undefined){
        res.send({
            success: false,
            response: "Not registered"
        })
    }else if(users[req.body.username].password!=req.body.password){
        res.send({
            success: false,
            response: "Invalid userID or password"
        })
    }else{
        let imageAsBase64 = fs.readFileSync(`./pfp/${req.body.username}.jpg`, 'base64');
        res.send({
            success: true,
            response: "Success",
            user: users[req.body.username],
            pfp: imageAsBase64
        })
    }    
})

server.listen(3000, function(err){
    if (err) console.log("Error in server setup")
    console.log("Server listening on Port", 3000);
})