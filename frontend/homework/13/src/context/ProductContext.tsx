import { createContext, useState } from "react";
import { IProduct, ECommerceType } from "../interfaces/interfaces";

interface ECommerceProviderProps {
    children: React.ReactNode;
}

export const ECommerceContext = createContext<ECommerceType>({
    productLists: [],
    product: null,
    loading: false,
    toggleLoading: () => {},
    setProducts: () => {},
    filter: "",
    sort: "",
    handleFilter: () => {},
    handleSort: () => {},
    search: "",
    handleSearch: () => {},
    filterProductLists: [],
    handleLists: () => {},
    handleProduct: () => {},
});

export const ECommerceProvider = ({ children }: ECommerceProviderProps) => {
    const [productLists, setProductLists] = useState<IProduct[]>([]);
    const [product, setProduct] = useState<IProduct | null>(null);
    const [loading, setLoading] = useState<boolean>(false);
    const [filter, setFilter] = useState<string>("");
    const [sort, setSort] = useState<string>("");
    const [search, setSearch] = useState<string>("");
    const [filterProductLists, setFilterProductLists] =
        useState<IProduct[]>(productLists);

    const handleSearch = (searchStr: string) => {
        setSearch(searchStr);
    };

    const toggleLoading = (state: boolean) => {
        setLoading(state);
    };

    const setProducts = (response: IProduct[]) => {
        setProductLists(response);
    };

    const handleFilter = (filter: string) => {
        setFilter(filter);
    };

    const handleSort = (sort: string) => {
        setSort(sort);
    };

    const handleLists = (filterProductLists: IProduct[]) => {
        setFilterProductLists(filterProductLists);
    };

    const handleProduct = (product: IProduct) => {
        setProduct(product);
    };

    return (
        <ECommerceContext.Provider
            value={{
                productLists,
                product,
                loading,
                filter,
                sort,
                search,
                filterProductLists,
                toggleLoading,
                setProducts,
                handleFilter,
                handleSort,
                handleSearch,
                handleLists,
                handleProduct,
            }}
        >
            {children}
        </ECommerceContext.Provider>
    );
};