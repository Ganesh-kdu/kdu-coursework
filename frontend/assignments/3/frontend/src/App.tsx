import Header from "./components.tsx/navbar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Dashboard from "./pages/Dashboard";
import { useDispatch } from "react-redux";
import { store } from "./redux/store";
import { fetchStocks, fetchHistory } from "./redux/thunk";
import Stock from "./pages/Stock";
import Portfolio from "./pages/Portfolio";
import Summarizer from "./pages/Summarizer";
import { Socket } from "socket.io-client";

function App({ socket }: Readonly<{ socket: Socket }>) {
    type AppDispatch = typeof store.dispatch;
    const reduxDispatch = useDispatch<AppDispatch>();
    reduxDispatch(fetchStocks());
    reduxDispatch(fetchHistory());
    return (
        <BrowserRouter>
            <Header />
            <Routes>
                <Route path="/" element={<Dashboard />} />
                <Route path="/:id" element={<Stock socket={socket} />} />
                <Route path="/summarizer" element={<Summarizer />} />
                <Route path="/portfolio" element={<Portfolio />} />
            </Routes>
        </BrowserRouter>
    );
}

export default App;