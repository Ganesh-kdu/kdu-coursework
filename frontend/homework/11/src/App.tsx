import { useEffect, useState } from "react";
import "./App.scss";
import NewQuote from "./components/newQuote";
import Filters from "./components/Filters";
import Quotes from "./components/quotes";
import IQuote from "./IQuote";

function App() {
    const [quotes, setQuotes] = useState<IQuote[]>([]);
    const [filters, setFilters] = useState<string[]>(["Filters","dfgs"]);
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
        <div className="container">
          <NewQuote addQuote = {setQuotes}/>
          <Filters filters={filters} setFilters={setFilters}/>
          <hr></hr>
          <Quotes quotes = {quotes} filters = {filters} addFilter = {setFilters}/>
        </div>
    );
}

export default App;
