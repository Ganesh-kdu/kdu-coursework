import Header from "./components.tsx/navbar";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import Dashboard from "./pages/Dashboard";

function App() {
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
