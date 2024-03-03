import { createAsyncThunk } from "@reduxjs/toolkit";

const fetchStocks = createAsyncThunk("fetchProducts", async () => {
    const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json");
    const data = await response.json();
    return data.map((stock:any,index: number)=>{return {
        id:index,
        stockName: stock.stock_name,
        stockSymbol: stock.stock_symbol,
        basePrice: stock.base_price
    }});
});

export default fetchStocks;