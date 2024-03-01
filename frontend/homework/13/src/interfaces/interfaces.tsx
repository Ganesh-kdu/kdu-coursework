export interface IProduct {
    id: number;
    title: string;
    price: number;
    description: string;
    category: string;
    image: string;
    rating: {
        rate: number;
        count: number;
    };
}

export interface ECommerceType {
    productLists: IProduct[];
    product: IProduct | null;
    loading: boolean;
    toggleLoading: (state: boolean) => void;
    setProducts: (response: IProduct[]) => void;
    filter: string;
    sort: string;
    handleFilter: (filter: string) => void;
    handleSort: (sort: string) => void;
    search: string;
    handleSearch: (searchStr: string) => void;
    filterProductLists: IProduct[];
    handleLists: (filterProductLists: IProduct[]) => void;
    handleProduct: (product: IProduct) => void;
}
