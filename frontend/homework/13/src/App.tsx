import Home from "./components/home";
import Navbar from "./components/navbar";
import Product from "./components/products";
import "./styles/App.scss";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <>
      <Navbar />
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />}/>
          <Route path="/:id" element={<Product/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
