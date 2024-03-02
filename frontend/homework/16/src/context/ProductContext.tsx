import { createContext, useEffect, useState } from "react";
import { IProduct, ECommerceType } from "../interfaces/interfaces";

interface ECommerceProviderProps {
    children: React.ReactNode;
}

export const ECommerceContext = createContext<ECommerceType>({
    productLists: [],
    displayList: [],
    loading: false,
    toggleLoading: () => {},
    setProducts: () => {},
    filter: "",
    sort: "",
    handleFilter: () => {},
    handleSort: () => {},
    search: "",
    handleSearch: () => {},
});

export const ECommerceProvider = ({ children }: ECommerceProviderProps) => {
    const [productLists, setProductLists] = useState<IProduct[]>([]);
    const [loading, setLoading] = useState<boolean>(false);
    const [filter, setFilter] = useState<string>("");
    const [sort, setSort] = useState<string>("");
    const [search, setSearch] = useState<string>("");
    const [displayList, setDisplayList] = useState<IProduct[]>([]);
    useEffect(() => {
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
        setDisplayList(tempList);
    }, [filter, sort, search, productLists]);
    return (
        <ECommerceContext.Provider
            value={{
                productLists: productLists,
                displayList: displayList,
                loading: loading,
                toggleLoading: setLoading,
                setProducts: setProductLists,
                filter: filter,
                sort: sort,
                search: search,
                handleFilter: setFilter,
                handleSort: setSort,
                handleSearch: setSearch,
            }}
        >
            {children}
        </ECommerceContext.Provider>
    );
};
