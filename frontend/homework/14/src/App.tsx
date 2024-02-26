import { createContext, useMemo, useState } from "react";
import Header from "./components/header";
import MainContainer from "./components/container";
export const SearchContext = createContext<{searchQuery:string, setQuery:Function}|null>(null);
function App() {
    const [searchQuery, setSearchQuery] = useState("");
    const searchObj = useMemo(() => ({searchQuery : searchQuery, setQuery : setSearchQuery}),[searchQuery]); 
    return (
        <SearchContext.Provider value={searchObj}>
            <Header/>
            <MainContainer/>
        </SearchContext.Provider>
    );
}
export default App;
