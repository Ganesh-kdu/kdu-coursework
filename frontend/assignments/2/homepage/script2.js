// Function to render a single post
function renderPost(postDetails) {
    const { uuid, profilePic, accountName, userName, postTime, postData } = postDetails;
    return `
        <div class="post_container" id="${uuid}">
            <div class="post">
                <div class="profile_picture">
                    <img class="post_user_image" src="${profilePic}" alt=""/>
                </div>
                <div class="wrapper">
                    <div class="user">
                        <div class="details">
                            <div class="detail_wrapper">
                                <div class="account_name">${accountName}</div>
                            </div>
                            <div class="detail_wrapper">
                                <div class="username">@${userName}</div>
                            </div>
                            <div class="detail_wrapper">
                                <div class="spacer">·</div>
                            </div>
                            <div class="detail_wrapper">
                                <div class="post_time">${postTime}</div>
                            </div>
                        </div>
                        <div class="kebab_wrapper">
                            <svg class="kebab_menu grey" viewBox="0 0 32 32">
                                <path d="M3 12c0-1.1.9-2 2-2s2 .9 2 2-.9 2-2 2-2-.9-2-2zm9 2c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2zm7 0c1.1 0 2-.9 2-2s-.9-2-2-2-2 .9-2 2 .9 2 2 2z"></path>
                            </svg>
                        </div>
                    </div>
                    <div class="post_data">${postData}</div>
                    <div class="stats">
                        <!-- Stats HTML -->
                    </div>
                </div>
            </div>
        </div>`;
}

// Function to handle like, retweet, and comment actions
function handleInteraction(event, className) {
    const currentTarget = event.currentTarget;
    const statValue = currentTarget.querySelector(".stat_value");

    if (currentTarget.classList.contains(className)) {
        if (statValue.textContent === "1") {
            statValue.textContent = "";
        } else {
            const value = parseInt(statValue.textContent);
            statValue.textContent = `${value - 1}`;
        }
    } else {
        if (!statValue.textContent) {
            statValue.textContent = "1";
        } else {
            const value = parseInt(statValue.textContent);
            statValue.textContent = `${value + 1}`;
        }
    }

    currentTarget.classList.toggle(className);
}

// Function to create a new post
function createPost() {
    const postDetails = {
        uuid: uuid(), // Assuming uuid() generates a unique ID
        profilePic: "../twiiter base line images/Profile/profile pic.png", // Example profile picture URL
        accountName: "Nitesh Gupta", // Example account name
        userName: "nit_hck", // Example username
        postTime: "1s", // Example post time
        postData: document.querySelector('#postText').value, // Post text from textarea
    };

    const file = document.querySelector('#image_button').files[0];
    if (file) {
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = function () {
            localStorage.setItem(`${postDetails.uuid}`, reader.result);
            const imageNode = document.createElement("img");
            imageNode.setAttribute("src", localStorage.getItem(`${postDetails.uuid}`));
            imageNode.classList.add("limit");
            postDetails.postData = `${postDetails.postData}<br><br>`;
            document.getElementById(postDetails.uuid.toString()).querySelector(".post_data").appendChild(imageNode);
        };
    }

    const postHTML = renderPost(postDetails);
    document.querySelector('.posts').insertAdjacentHTML("afterbegin", postHTML);

    // Attach event listeners for like, retweet, and comment
    const postElement = document.getElementById(postDetails.uuid);
    postElement.querySelector(".retweet").addEventListener("click", (event) => handleInteraction(event, "retweeted"));
    postElement.querySelector(".comment").addEventListener("click", (event) => handleInteraction(event, "commented"));
    postElement.querySelector(".like").addEventListener("click", (event) => handleInteraction(event, "like-post"));
}

// Function to toggle post screen visibility
function postScreen() {
    document.querySelector(".reactive_top").classList.toggle("invisible");
    document.querySelector(".posts").classList.toggle("invisible");
    document.querySelector(".category").classList.toggle("invisible");
    document.querySelector('.tweet-box').classList.toggle("visible");
    document.querySelector('.reactive_post_navigation').classList.toggle("invisible");
}

// Function to handle creating a post on mobile
function mobileCreatePost() {
    createPost();
    postScreen();
}

// Example usage of setting user details from session storage
const userDetails = JSON.parse(sessionStorage.getItem("details"));
document.getElementById("logged-in-user-1").setAttribute("src", `data:image/png;base64, ${userDetails.pfp}`);
document.getElementById("logged-in-user-2").setAttribute("src", `data:image/png;base64, ${userDetails.pfp}`);
document.getElementById("logged-in-user-3").setAttribute("src", `data:image/png;base64, ${userDetails.pfp}`);
document.getElementById("account-name-1").innerText = userDetails.user_name;
document.getElementById("account-id-1").innerText = userDetails.profile_url;
