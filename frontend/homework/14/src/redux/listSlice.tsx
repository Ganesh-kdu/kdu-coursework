import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface CounterState {
    list: string[];
}

const initialState: CounterState = {
    list: [],
};
const listSlice = createSlice({
    name: "list",
    initialState,
    reducers: {
        addToList: (state, action: PayloadAction<string>) => {
            state.list = [...state.list, action.payload];
        },
        setList: (state, action: PayloadAction<string[]>) => {
            state.list = action.payload;
        },
    },
});

export const { addToList, setList } = listSlice.actions;
export default listSlice.reducer;
