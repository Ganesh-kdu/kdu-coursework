import { configureStore } from "@reduxjs/toolkit";
import listReducer from "./hotelSlice";

export const store = configureStore({
    reducer: {
        list: listReducer,
    },
});

export type RootState = ReturnType<typeof store.getState>;