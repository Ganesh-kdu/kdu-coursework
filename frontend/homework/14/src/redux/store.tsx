import { configureStore } from "@reduxjs/toolkit";
import listReducer from "./listSlice";
import searchReducer from "./searchSlice";

export const store = configureStore({
    reducer: {
        list: listReducer,
        search: searchReducer,
    },
});

export type RootState = ReturnType<typeof store.getState>;
