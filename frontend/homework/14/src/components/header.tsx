import { SearchContext } from "../App";
import "./header.scss";
import React, { useContext } from "react";
function Header() {
    const context = useContext(SearchContext);
    const { setQuery } = context as { searchQuery: string; setQuery: (searchQuery: string) => void };
    const querySetter = (e: React.ChangeEvent<HTMLInputElement>) => {
        setQuery(e.target.value);
    };

    return (
        <div className="header">
            <h1 className="title">Item Lister</h1>
            <input
                type="text"
                id="search"
                placeholder="Search Items..."
                onChange={querySetter}
            ></input>
        </div>
    );
}
export default Header;
