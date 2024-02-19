async function login(){

    let rawResponse = await fetch("http://127.0.0.1:3000/api/user/login", {
        method: 'POST',
        headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
        },
        body: JSON.stringify({username: document.getElementById("username").value, password: document.getElementById("password").value})
    });
    let response = await rawResponse.json();
    console.log(response);
    if(!response.success){
        window.alert(response.response);
    }else{
        response.user.pfp = response.pfp;
        sessionStorage.setItem("details", JSON.stringify(response.user));
        window.location.href = "http://127.0.0.1:5500/homepage/index.html";
    }
}