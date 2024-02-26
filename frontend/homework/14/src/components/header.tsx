import { useDispatch } from "react-redux";
import { setQuery } from "../redux/searchSlice";

import "./header.scss";
function Header() {
    const reduxDispatch = useDispatch();

    const querySetter = (e: React.ChangeEvent<HTMLInputElement>) => {
        reduxDispatch(setQuery(e.target.value));
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
