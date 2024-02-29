import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage';
import listReducer from "./listSlice";
import searchReducer from "./searchSlice";

const persistConfig = {
  key: 'root',
  storage,
  blacklist: ['search'],
};

const persistedReducer = persistReducer(persistConfig, combineReducers({
  list: listReducer,
  search: searchReducer,
}));

export const store = configureStore({
    reducer: persistedReducer,
});

export const persistor = persistStore(store);

export type RootState = ReturnType<typeof store.getState>;
export type StoreType = typeof store;
