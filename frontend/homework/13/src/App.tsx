import Home from "./components/home";
import Navbar from "./components/navbar";
import Product from "./components/products";
import "./styles/App.scss";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ECommerceContext } from "./context/ProductContext";
import { useContext, useEffect } from "react";

function App() {
    const { setProducts, handleFilter, toggleLoading } =
        useContext(ECommerceContext);

    const fetchData = async (): Promise<void> => {
        toggleLoading(true);
        const response = await fetch("https://fakestoreapi.com/products");
        setProducts(await response.json());
        toggleLoading(false)
    };

    useEffect(() => {
        fetchData();
        const searchString = window.location.search;
        const params = new URLSearchParams(searchString);
        handleFilter(params.get("filter") ?? "");
    }, []);

    return (
        <>
            <Navbar />
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/:id" element={<Product />} />
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;