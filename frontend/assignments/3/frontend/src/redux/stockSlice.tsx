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
        watchToggle: (state, action: PayloadAction<number>) => {
            state.watchList[action.payload] = !state.watchList[action.payload];
        }
    },
    extraReducers(builder) {
        builder.addCase(fetchStocks.pending, (state) => {
            state.loading = "pending";
        });
        builder.addCase(fetchStocks.fulfilled, (state, action) => {
            state.loading = "succeeded";
            state.stockList = action.payload;
            state.watchList = Array<boolean>(state.stockList.length).fill(false);
        });
        builder.addCase(fetchStocks.rejected, (state) => {
            state.loading = "failed";
        });
    },
});
export const { setStocks, watchToggle } =
    productSlice.actions;
export default productSlice.reducer;