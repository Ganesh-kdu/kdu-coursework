const socket = io("http://127.0.0.1:3000");

socket.on("new-user", (payload) => {
  createUser(payload);
});
socket.on("new-users", (payload) => {
  payload.map((user) => {
    createUser(user);
  });
});
let selected = "";
function createUser(user) {
  console.log(user);
  let chatButton = document.createElement("button");
  chatButton.id = user.user_name;
  chatButton.classList.add("chat-button");

  let pfp = document.createElement("img");
  pfp.setAttribute("src", `data:image/png;base64, ${user.pfp}`);
  pfp.classList.add("pfp");

  let username = document.createElement("div");
  username.style.padding = "5px";
  username.innerText = user.user_name;

  let profileUrl = document.createElement("div");
  profileUrl.innerText = user.profile_url;
  profileUrl.style.color = "grey";

  chatButton.appendChild(pfp);
  chatButton.appendChild(username);
  chatButton.appendChild(profileUrl);
  document.getElementById("chats").appendChild(chatButton);

  let chat = document.createElement("div");
  chat.id = `${user.user_name}_chat`;
  chat.style.display = "none";
  chat.classList.add("chat");

  let chatTitle = document.createElement("div");

  let backArrow = document.createElement("div");
  backArrow.classList.add("back-arrow");
  backArrow.addEventListener("click", () => {});
  let arrowSvg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  arrowSvg.setAttribute("class", "option_image");
  arrowSvg.setAttribute("viewBox", "0 0 35 35");
  let arrowPath = document.createElementNS(
    "http://www.w3.org/2000/svg",
    "path"
  );
  arrowPath.setAttribute(
    "d",
    "M7.414 13l5.043 5.04-1.414 1.42L3.586 12l7.457-7.46 1.414 1.42L7.414 11H21v2H7.414z"
  );
  arrowSvg.appendChild(arrowPath);
  backArrow.appendChild(arrowSvg);

  chatTitle.appendChild(backArrow);
  let usernameDiv = document.createElement("div");
  usernameDiv.innerText = user.user_name;
  chatTitle.appendChild(usernameDiv);
  chatTitle.classList.add("chat-title");

  let messageArea = document.createElement("div");
  messageArea.classList.add("message-area");

  let textBox = document.createElement("div");
  textBox.classList.add("text-box");

  let innerBox = document.createElement("div");
  innerBox.classList.add("inner-box");

  let input = document.createElement("input");
  input.classList.add("input");
  input.placeholder = "Start a new message";

  let send = document.createElement("button");
  send.classList.add("send-button");

  let svg = document.createElementNS("http://www.w3.org/2000/svg", "svg");
  svg.setAttribute("class", "send-svg");
  svg.setAttribute("viewBox", "0 0 25 25");
  let path = document.createElementNS("http://www.w3.org/2000/svg", "path");
  path.setAttribute(
    "d",
    "M2.504 21.866l.526-2.108C3.04 19.719 4 15.823 4 12s-.96-7.719-.97-7.757l-.527-2.109L22.236 12 2.504 21.866zM5.981 13c-.072 1.962-.34 3.833-.583 5.183L17.764 12 5.398 5.818c.242 1.349.51 3.221.583 5.183H10v2H5.981z"
  );

  svg.appendChild(path);
  send.appendChild(svg);

  innerBox.appendChild(input);
  innerBox.appendChild(send);
  textBox.appendChild(innerBox);

  chat.appendChild(chatTitle);
  chat.appendChild(messageArea);
  chat.appendChild(textBox);
  document.getElementById("chat").appendChild(chat);

  chatButton.addEventListener("click", () => {
    document.querySelectorAll(".chat").forEach((chatDiv) => {
      chatDiv.style.display = "none";
    });
    selected = chatButton.id;
    chat.style.display = "flex";
  });
  send.addEventListener("click", () => {
    const message = input.value.trim();
    if (message !== "") {
      let payload = {
        from: JSON.parse(sessionStorage.getItem("details")).user_name,
        user: selected,
        message: message,
      };
      socket.emit("message", payload);
      input.value = "";
      displayMessage(payload.user, payload.message, true);
    }
  });
}
socket.on("remove-user", (payload) => {
  const chatDiv = document.getElementById(payload);
  if (chatDiv) {
    chatDiv.remove();
    const userChat = document.getElementById(`${payload}_chat`);
    userChat.remove();
  }
});
socket.emit("register", JSON.parse(sessionStorage.getItem("details")));
socket.on("new-message", (payload) => {
  console.log(payload);
  displayMessage(payload.from, payload.message, false);
});

function displayMessage(user, message, sent) {
  const messageContainer = document.createElement("div");
  messageContainer.classList.add(sent ? "sent-message" : "received-message");

  const messageText = document.createElement("div");
  messageText.innerText = message;

  messageContainer.appendChild(messageText);

  const chatArea = document
    .getElementById(`${user}_chat`)
    .querySelector(".message-area");
  chatArea.appendChild(messageContainer);
}
