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
    displayList: IProduct[];
    loading: string;
    filter: string;
    sort: string;
    search: string;
}
