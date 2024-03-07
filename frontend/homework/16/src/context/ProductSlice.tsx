import { PayloadAction, createSlice } from "@reduxjs/toolkit";
import { ECommerceType, IProduct } from "../interfaces/interfaces";
import fetchProducts from "./thunk";

const initialState: ECommerceType = {
    productLists: [],
    displayList: [],
    loading: '',
    filter: "",
    sort: "",
    search: "",
};

function helper(productLists: IProduct[], search: string, filter: string, sort: string) {
    let tempList = productLists.filter(
        (listItem: IProduct, _index: number) => {
            return RegExp(search).exec(listItem.title);
        }
    );

    const sortByPriceAsc = (a: IProduct, b: IProduct) => {
        return a.price - b.price;
    };
    const sortByPriceDesc = (a: IProduct, b: IProduct) => {
        return b.price - a.price;
    };
    if (sort == "ASC") tempList = tempList.sort(sortByPriceAsc);
    else tempList = tempList.sort(sortByPriceDesc);

    tempList = tempList.filter((listItem: IProduct, _index: number) => {
        return RegExp(filter).exec(listItem.category);
    });
    return tempList;
}
const productSlice = createSlice({
    name: "list",
    initialState,
    reducers: {
        toggleLoading: (state, action: PayloadAction<string>) => {
            state.loading = action.payload;
        },
        setProducts: (state, action: PayloadAction<IProduct[]>) => {
            state.productLists = action.payload;
            state.displayList = helper(state.productLists, state.search, state.filter, state.sort);
        },
        handleFilter: (state, action: PayloadAction<string>) => {
            state.filter = action.payload;
            state.displayList = helper(state.productLists, state.search, state.filter, state.sort);
        },
        handleSort: (state, action: PayloadAction<string>) => {
            state.sort = action.payload;
            state.displayList = helper(state.productLists, state.search, state.filter, state.sort);
        },
        handleSearch: (state, action: PayloadAction<string>) => {
            state.search = action.payload;
            state.displayList = helper(state.productLists, state.search, state.filter, state.sort);
        },
    },
    extraReducers(builder) {
        builder.addCase(fetchProducts.pending, (state) => {
            state.loading = "pending";
        });
        builder.addCase(fetchProducts.fulfilled, (state, action) => {
            state.loading = "succeeded";
            state.productLists = action.payload;
            state.displayList = helper(state.productLists, state.search, state.filter, state.sort);
        });
        builder.addCase(fetchProducts.rejected, (state) => {
            state.loading = "failed";
        });
    },
});
export const { toggleLoading, setProducts, handleFilter, handleSort, handleSearch } =
    productSlice.actions;
export default productSlice.reducer;
