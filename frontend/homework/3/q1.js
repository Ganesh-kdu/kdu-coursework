function tip(billAmount) {
    if (billAmount < 50) {
        return billAmount * 0.2;
    } else if (billAmount < 200) {
        return billAmount * 0.15;
    } else {
        return billAmount * 0.1;
    }
}
function tipCalculator(amounts) {
    let tips = [];
    let totals = [];
    amounts.forEach((amount) => {
        let tipAmount = tip(amount);
        tips.push(tipAmount);
        totals.push(amount + tipAmount);
    });
    console.log(tips);
    console.log(totals);
}
tipCalculator([20, 100, 1000]);
