import { useEffect, useState } from "react";
import "./App.scss";
import NewQuote from "./components/newQuote";
import Filters from "./components/Filters";
import Quotes from "./components/quotes";
import IQuote from "./IQuote";

function App() {
    const [quotes, setQuotes] = useState<IQuote[]>([]);
    const [filters, setFilters] = useState<string[]>(["dsd","dfgs"]);
    const getData = async () => {
      const response = await fetch(
          "https://api.quotable.io/quotes/random?limit=3"
      );
      const result = await response.json();
      setQuotes(result);
      console.log(result);
  };
    useEffect(() => {
        getData();
    }, []);
    return (
        <div>
          <NewQuote addQuote = {setQuotes}/>
          <Filters f={filters} />
          <Quotes quotes = {quotes} filters = {filters} addFilter = {setFilters}/>
        </div>
    );
}

export default App;
