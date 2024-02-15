var socket = io("http://127.0.0.1:3000");
var form = document.getElementById("form");
var input = document.getElementById("input");

form.addEventListener("submit", function(e) {
    e.preventDefault();
    if (input.value.trim() !== "") {
        console.log(input.value)
        socket.emit("message", input.value);
        input.value = "";
    }
    newMessage(input.value, sent=true);
  });

socket.on("new-message", (payload) => {
    console.log(`Message received by client: ${payload}`);
});
socket.on("connected",(payload)=>{
    console.log(payload);
})
function newMessage(message, sent=false){
    let messageContainer = document.createElement("div");
    messageContainer.classList.add("container");

    let profilePicture = document.createElement("div");
    if(sent){
        messageContainer.classList.add("sent");
        profilePicture.innerText = "Y";
    }else{
        messageContainer.classList.add("received");
        profilePicture.innerText = "G"
    }
    profilePicture.classList.add("pfp")

    let messageBox = document.createElement("div");
    messageBox.innerText = message;
    messageBox.classList.add("message-box");

    messageContainer.appendChild(profilePicture);
    messageContainer.appendChild(messageBox);

    document.getElementById("chat").appendChild(messageContainer);


}