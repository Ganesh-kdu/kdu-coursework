export interface IStock {
    id: number;
    stockName: string;
    stockSymbol: string;
    basePrice: number;
}

export interface IStockMarket {
    stockList: IStock[];
    watchList: boolean[];
    loading: string;
    balance: number;
    trades: ITransaction[];
}

export interface IStockReceived {
    stock_name: string;
    stock_symbol: string;
    base_price: number;
}

export interface ITransaction {
    stock_name: string;
    stock_symbol: string;
    transaction_price: number;
    timestamp: string;
    status: string;
}
export interface IDisplayTransaction {
    qty: number;
    timestamp: string;
    type: string;
}

export interface IDay {
    date: string;
    prices: number[];
}
export interface ISummarizerStock {
    company: string;
    symbol: string;
    data: IDay[];
}

export interface IWorkerResult {
    buyDate: string;
    buyPrice: number;
    sellDate: string;
    sellPrice: number;
    profit: number;
}

export interface ILiveTrade {
    qty: number;
    name: string;
    type: string;
}
