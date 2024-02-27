import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface CounterState {
    searchQuery: string;
}

const initialState: CounterState = {
    searchQuery: "",
};
const searchSlice = createSlice({
    name: "search",
    initialState,
    reducers: {
        setQuery: (state, action: PayloadAction<string>) => {
            state.searchQuery = action.payload;
        },
    },
});

export const { setQuery } = searchSlice.actions;
export default searchSlice.reducer;
