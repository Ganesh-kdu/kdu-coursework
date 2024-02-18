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
io.on("connection", async (socket) => {
    console.log("New connection");
    socket.on("message", (payload) => {
        console.log(`Message received on server: ${payload}`);
        io.except(socket.id).emit("new-message", payload);
    });
    while(true){
        await timeout(5000);
        socket.emit("latest-price",Math.random() * 500);
    }

});

function timeout(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}


app.get("/zomato", (req, res) => {
    let imageAsBase64 = fs.readFileSync('./zomato_logo.jpg', 'base64');

    res.send({
        name:"Zomato",
        price:10.00,
        history:{},
        img:imageAsBase64
    })
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
        let imageAsBase64 = fs.readFileSync(`./pfp/${req.body.username}.png`, 'base64');
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