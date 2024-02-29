import { PayloadAction, createSlice } from "@reduxjs/toolkit";

interface CounterState {
    list: string[];
    checked: number[];
}

const initialState: CounterState = {
    list: [],
    checked: [],
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
        check: (state, action: PayloadAction<number>) => {
            if (state.checked.includes(action.payload)) {
                state.checked = state.checked.filter(
                    (item) => item !== action.payload
                );
            } else {
                state.checked = [...state.checked, action.payload];
            }
        },
        clear: (state) => {
            state.checked = [];
        },
    },
});

export const { addToList, setList, check, clear } = listSlice.actions;
export default listSlice.reducer;
