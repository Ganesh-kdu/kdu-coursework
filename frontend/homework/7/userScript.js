const socket = io("http://127.0.0.1:3000");
const form = document.getElementById("form");
const input = document.getElementById("input");

form.addEventListener("submit", function(e) {
    e.preventDefault();
    if (input.value.trim() !== "") {
        console.log(input.value)
        socket.emit("message", input.value);
        newMessage(input.value, true);
        input.value = "";
    }
  });

socket.on("new-message", (payload) => {
    newMessage(payload)
});
socket.on("connected",(payload)=>{
    console.log(payload);
})
function newMessage(message, sent=false){
    let messageContainer = document.createElement("div");
    messageContainer.classList.add("container");
    
    let profilePicture = document.createElement("div");

    let messageBox = document.createElement("div");
    messageBox.innerText = message;
    messageBox.classList.add("message-box");
    
    if(sent){
        messageBox.classList.add("sent");
        profilePicture.innerText = "Y";
    }else{
        messageBox.classList.add("received");
        profilePicture.innerText = "G"
    }
    profilePicture.classList.add("pfp")


    messageContainer.appendChild(profilePicture);
    messageContainer.appendChild(messageBox);

    let chat = document.getElementById("chat");

    chat.appendChild(messageContainer);
    chat.scrollTop = chat.scrollHeight - chat.clientHeight;


}