const socket = io("http://127.0.0.1:3000");
const form = document.getElementById("form");
const input = document.getElementById("input");


socket.on("latest-price", (payload) => {
    createBar(payload);
    updatePrice(payload);
    previous=payload;
    console.log(payload)
});
let previous = 1;
function updatePrice(price){
    let priceDiv = document.getElementById("price-div");
    priceDiv.classList.remove("increase");
    priceDiv.classList.remove("decrease");
    priceDiv.innerText=parseFloat(price).toFixed(2);
    if(price>previous)
        priceDiv.classList.add("increase");
    else
        priceDiv.classList.add("decrease");

    let change = document.getElementById("change");
    change.innerText = parseFloat(Math.abs(100*(previous-price)/previous)).toFixed(2);
    
}
function createBar(price){
    let bar = document.createElement("div");
    bar.classList.add("bar");
    if(price>previous)
        bar.classList.add("increase");
    else
        bar.classList.add("decrease");
    bar.style.height=price+"px";
    document.getElementById("graph").appendChild(bar);
}
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
    createBar(data.price);
    price.classList.add("price");
    price.id="price-div";
    if(data.price>previous)
        price.classList.add("increase");
    else
        price.classList.add("decrease");
    document.getElementById("stock-price").appendChild(price);

    let change = document.createElement("div");
    change.innerText = "0.00";
    change.classList.add("change");
    change.id="change";
    document.getElementById("stock-price").appendChild(change);

}
function buy(){
    buyDiv = document.createElement("div");
    buyDiv.innerText = `BUY QTY: ${document.getElementById("qty").value} Price: ${parseFloat(previous).toFixed(2)}`
    document.getElementById("history").appendChild(buyDiv)
}
function sell(){
    buyDiv = document.createElement("div");
    buyDiv.classList.add("history-item");
    buyDiv.innerText = `SELL QTY: ${document.getElementById("qty").value} Price: ${parseFloat(previous).toFixed(2)}`
    document.getElementById("history").appendChild(buyDiv)
}
getStock('zomato')