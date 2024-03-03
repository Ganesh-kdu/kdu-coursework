import Header from "./components.tsx/navbar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import { useDispatch } from "react-redux";
import { store } from "./redux/store";
import fetchStocks from "./redux/thunk";

function App() {
    type AppDispatch = typeof store.dispatch;
    const reduxDispatch = useDispatch<AppDispatch>();
    reduxDispatch(fetchStocks());
    return (
        <>
            <Header />
            <BrowserRouter>
                <Routes>
                    <Route path="/" element={<Dashboard />} />
                    {/* <Route path="/:id" element={<Product />} /> */}
                </Routes>
            </BrowserRouter>
        </>
    );
}

export default App;