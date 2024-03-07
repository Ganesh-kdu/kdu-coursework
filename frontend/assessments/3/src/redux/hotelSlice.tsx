import { PayloadAction, createSlice } from "@reduxjs/toolkit";
export interface IAddOn {
    name: string;
    cost: string;
    currency: string;
}

export interface IRoom {
    id: number;
    name: string;
    costPerNight: string;
    currency: string;
    addOns: IAddOn[];
}
export interface HotelState {
    rooms: IRoom[];
    selected: string | null;
    preferences: IAddOn[];
    bill: number;
    duration: number;
}

const initialState: HotelState = {
    rooms: [],
    selected: null,
    preferences: [],
    bill: 0,
    duration: -1
};
const listSlice = createSlice({
    name: "list",
    initialState,
    reducers: {
        setRooms: (state, action: PayloadAction<IRoom[]>) => {
            state.rooms = [...action.payload];
        },
        selectRoom: (state, action: PayloadAction<IRoom>) => {
            state.selected = action.payload.id.toString();
            state.preferences = [];
            state.bill = parseInt(action.payload.costPerNight);
        },
        resetPreferences: (state) => {
            state.preferences = [];
        },
        togglePreference: (state, action: PayloadAction<IAddOn>) => {
            let contains = false;
            state.preferences = state.preferences.filter((preference) => {
                if (preference.name === action.payload.name) contains = true;
                return preference.name !== action.payload.name;
            });
            if (contains) {
                state.bill -= parseInt(action.payload.cost);
            } else {
                state.preferences = [...state.preferences, action.payload];
                state.bill = state.bill + parseInt(action.payload.cost);
            }
        },
        setDuration: (state, action: PayloadAction<number>) => {
            state.duration = action.payload;
        }
    },
});

export const { setRooms, selectRoom, togglePreference, setDuration } = listSlice.actions;
export default listSlice.reducer;
