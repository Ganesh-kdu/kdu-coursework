import Home from "./components/home";
import Navbar from "./components/navbar";
import Product from "./components/product";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { useDispatch } from "react-redux";
import fetchProducts from "./context/thunk";
import { store } from "./context/Store";
import { SnackBar } from "./components/snackbar";

function App() {
    type AppDispatch = typeof store.dispatch;
    const reduxDispatch = useDispatch<AppDispatch>();
    reduxDispatch(fetchProducts());
    return (
        <>
            <Navbar />
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/:id" element={<Product />} />
                </Routes>
            </BrowserRouter>
            <SnackBar/>
        </>
    );
}

export default App;
