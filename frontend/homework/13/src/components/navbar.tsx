import "../styles/navbar.scss";
import { FaSearch } from "react-icons/fa";
function Navbar() {
    return (
        <div className="navbar">
            <div className="search">
                <input
                    type="text"
                    className="search-input"
                    id="search-input"
                    placeholder="Search..."
                />
                <button className="search-button">
                    <FaSearch />
                </button>
            </div>
            <div className="options">
                Filter:{" "}
                <select name="filters" id="filters" className="filters">
                    <option value="a">Volvo</option>
                    <option value="b">Saab</option>
                </select>
                Sort:{" "}
                <select name="sort" id="sort" className="sort">
                    <option value="1">Volvo</option>
                    <option value="2">Saab</option>
                </select>
            </div>
        </div>
    );
}
export default Navbar;
