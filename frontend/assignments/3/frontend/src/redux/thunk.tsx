import { createAsyncThunk } from "@reduxjs/toolkit";

const fetchStocks = createAsyncThunk("fetchProducts", async () => {
    const response = await fetch("https://kdu-automation.s3.ap-south-1.amazonaws.com/mini-project-apis/stocks.json");
    const data = await response.json();
    console.log(data);
    return data;
});

export default fetchStocks;