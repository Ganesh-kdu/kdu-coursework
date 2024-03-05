import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { fetchStocks, fetchHistory } from "./thunk";
import { IStock, IStockMarket, ITransaction } from "../interfaces/interfaces";

const initialState: IStockMarket = {
    stockList: [],
    watchList: [],
    loading: "",
    balance: 20000,
    trades: [],
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
        },
        addTransaction: (state, action: PayloadAction<ITransaction>) => {
            state.trades = [...state.trades, action.payload];
        },
        updateBalance: (state, action: PayloadAction<number>) => {
            state.balance = state.balance - action.payload;
        },
    },
    extraReducers(builder) {
        builder.addCase(fetchStocks.pending, (state) => {
            state.loading = "pending";
        });
        builder.addCase(fetchStocks.fulfilled, (state, action) => {
            state.loading = "succeeded";
            state.stockList = action.payload;
            state.watchList = Array<boolean>(state.stockList.length).fill(
                false
            );
        });
        builder.addCase(fetchStocks.rejected, (state) => {
            state.loading = "failed";
        });
        builder.addCase(fetchHistory.fulfilled, (state, action) => {
            state.trades = [...action.payload];
        });
    },
});
export const { setStocks, watchToggle, addTransaction, updateBalance } =
    productSlice.actions;
export default productSlice.reducer;
