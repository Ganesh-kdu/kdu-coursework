import "./header.scss";
import React from "react";
function Header({ setQuery }: any) {
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
