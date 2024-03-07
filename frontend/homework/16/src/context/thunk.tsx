import { createAsyncThunk } from "@reduxjs/toolkit";

const fetchProducts = createAsyncThunk("fetchProducts", async () => {
    const response = await fetch("https://fakestoreapi.com/products");
    const data = await response.json();
    console.log(data);
    return data;
});

export default fetchProducts;