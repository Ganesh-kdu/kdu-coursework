const shoes = [
    { type: 'sneakers', color: 'black', size: 10, price: 50 },
    { type: 'boots', color: 'brown', size: 9, price: 80 }
];
const shirts = [
    { type: 't-shirt', color: 'blue', size: 'M', price: 20 },
    { type: 'polo', color: 'white', size: 'L', price: 30 },
    { type: 'dress shirt', color: 'blue', size: 'S', price: 40 }
];

const warehouse = [...shoes, ...shirts];
const totalWorth = warehouse.reduce((acc, item) => acc + item.price);
warehouse.sort((a, b) => b.price - a.price);
const blueProducts = warehouse.filter(item => item.color === 'blue');

console.log("Warehouse:", warehouse);
console.log("Total worth of products in the warehouse:", totalWorth);
console.log("Blue products in the warehouse:", blueProducts);
