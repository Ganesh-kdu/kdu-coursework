const socket = io("http://127.0.0.1:3000");
const form = document.getElementById("form");
const input = document.getElementById("input");

// form.addEventListener("submit", function(e) {
//     e.preventDefault();
//     if (input.value.trim() !== "") {
//         console.log(input.value)
//         socket.emit("message", input.value);
//         newMessage(input.value, true);
//         input.value = "";
//     }
//   });

socket.on("new-message", (payload) => {
    newMessage(payload)
});
socket.on("connected",(payload)=>{
    console.log(payload);
})

async function getStock(stockName){
    let res = await fetch(`http://127.0.0.1:3000/${stockName}`);
    let data = await res.json()
    console.log(data)

    let img = document.createElement("img");
    img.src = `data:image/png;base64, ${data.img}`;
    document.getElementById("stock-label").appendChild(img);

    let label = document.createElement("div");
    label.innerText = data.name;
    document.getElementById("stock-label").appendChild(label);

    let price = document.createElement("div");
    price.innerText = parseFloat(data.price).toFixed(2);
    price.classList.add("price");
    price.classList.add("increase");
    document.getElementById("stock-price").appendChild(price);

    let change = document.createElement("div");
    change.innerText = "0.00";
    change.classList.add("change");
    document.getElementById("stock-price").appendChild(change);

}
getStock('zomato')