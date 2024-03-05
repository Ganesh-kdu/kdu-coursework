import { IDay, ISummarizerStock } from "../interfaces/interfaces";

self.onmessage = (event) => {
    const { stock, index } = event.data;
    console.log(stock);
    const maxProfit = calculateMaxProfit(stock);
    self.postMessage({ ...maxProfit, index });
};

function calculateMaxProfit(stock: ISummarizerStock) {
    let data = stock.data;
    let maxProfit = 0;
    let buyDate = data[0].date;
    let sellDate = data[0].date;
    let buyPrice = data[0].prices[0];
    let sellPrice = data[0].prices[0];
    let minPrice = data[0].prices[0];
    let minPriceDate = data[0].date;
    data.forEach((day: IDay) => {
        day.prices.forEach((price: number) => {
            if (price < minPrice) {
                minPrice = price;
                minPriceDate = day.date;
            }
            if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
                buyDate = minPriceDate;
                buyPrice = minPrice;
                sellDate = day.date;
                sellPrice = price;
            }
        });
    });

    return { buyDate, buyPrice, sellDate, sellPrice, profit: maxProfit };
}
