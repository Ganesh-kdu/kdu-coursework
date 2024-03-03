export interface IStock{
    id: number,
    stockName: string,
    stockSymbol: string,
    basePrice: number
}

export interface IStockMarket{
    stockList: IStock[],
    watchList: boolean[],
    loading:string,
}

export interface IStockReceived{
    stock_name: string,
    stock_symbol: string,
    base_price: number
}