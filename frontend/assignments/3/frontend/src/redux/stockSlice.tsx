import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import fetchStocks from "./thunk";
import { IStock, IStockMarket } from "../interfaces/interfaces";

const initialState: IStockMarket = {
    stockList: [],
    watchList: [],
    loading: ""
};

const productSlice = createSlice({
    name: "stockMarket",
    initialState,
    reducers: {
        setStocks: (state, action: PayloadAction<IStock[]>) => {
            state.stockList = action.payload;
        },
    },
    extraReducers(builder) {
        builder.addCase(fetchStocks.pending, (state) => {
            state.loading = "pending";
        });
        builder.addCase(fetchStocks.fulfilled, (state, action) => {
            state.loading = "succeeded";
            state.stockList = action.payload;
            console.log(state.stockList)
            // state.watchList = 
        });
        builder.addCase(fetchStocks.rejected, (state) => {
            state.loading = "failed";
        });
    },
});
export const { setStocks } =
    productSlice.actions;
export default productSlice.reducer;