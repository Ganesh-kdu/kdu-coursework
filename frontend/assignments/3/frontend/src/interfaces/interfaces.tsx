export interface IStock{
    id: number,
    stockName: string,
    stockSymbol: string,
    basePrice: number,
}

export interface IStockMarket{
    stockList: IStock[],
    watchList: boolean[],
    loading:string,
    balance:number
    trades: ITransaction[]
}

export interface IStockReceived{
    stock_name: string,
    stock_symbol: string,
    base_price: number
}

export interface ITransaction{
    stock_name: string,
    stock_symbol: string,
    transaction_price: number,
    timestamp: string,
    status: string
}
export interface IDisplayTransaction{
    qty: number,
    timestamp: string,
    type: string
}