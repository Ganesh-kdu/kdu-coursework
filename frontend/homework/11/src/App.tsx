import { useEffect, useState } from "react";
import "./App.scss";
import NewQuote from "./components/newQuote";
import Filters from "./components/Filters";
import Quotes from "./components/quotes";
import IQuote from "./IQuote";

function App() {
    const [quotes, setQuotes] = useState<IQuote[]>([]);
    const [filters, setFilters] = useState<Set<string>>(new Set());
    const getData = async () => {
      const response = await fetch(
          "https://api.quotable.io/quotes/random?limit=3"
      );
      const result = await response.json();
      setQuotes([...result]);
  };
    useEffect(() => {
        getData();
    }, []);
    return (
        <div className="container">
            <div className="sticky">
          <NewQuote addQuote = {setQuotes}/>
          <Filters filters={Array.from(filters)} setFilters={setFilters}/>
          </div>
          <Quotes quotes = {quotes} filters = {Array.from(filters)} addFilter = {setFilters}/>
        </div>
    );
}

export default App;
