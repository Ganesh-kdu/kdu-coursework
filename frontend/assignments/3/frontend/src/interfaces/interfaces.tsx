export interface IStock{
    "stockName": string,
    "stockSymbol": string,
    "basePrice": number
}

export interface IStockMarket{
    "stockList": IStock[],
    "watchList": boolean[],
    "loading":string,
}