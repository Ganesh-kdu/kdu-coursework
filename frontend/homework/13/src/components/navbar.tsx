import { useContext } from "react";
import "../styles/navbar.scss";
import { FaSearch } from "react-icons/fa";
import { ECommerceContext } from "../context/ProductContext";
import { useParams } from "react-router-dom";
function Navbar() {
    const { handleSearch, handleFilter, handleSort } =
        useContext(ECommerceContext);
    const querySetter = (e: React.ChangeEvent<HTMLInputElement>) => {
        handleSearch(e.target.value);
    };
    const filterSetter = (e: React.ChangeEvent<HTMLSelectElement>) => {
        handleFilter(e.target.value);
    };
    const sortSetter = (e: React.ChangeEvent<HTMLSelectElement>) => {
        handleSort(e.target.value);
    };
    const { id } = useParams();
    console.log(id)
    return (
        <div className="navbar">
            <div className="search">
                <input
                    type="text"
                    className="search-input"
                    id="search-input"
                    placeholder="Search..."
                    onChange={querySetter}
                />
                <button className="search-button">
                    <FaSearch />
                </button>
            </div>
            {id == undefined?
            <div className="options">
                <div className="option-title">Filters: </div>
                <select
                    name="filters"
                    id="filters"
                    className="filters"
                    onChange={filterSetter}
                >
                    <option value="">None</option>

                    <option value="men's clothing">Mens</option>
                    <option value="women's clothing">Womens</option>
                    <option value="jewelery">Jewellery</option>
                    <option value="electronics">Electronics</option>
                </select>
                <div className="option-title">Sort: </div>

                <select
                    name="sort"
                    id="sort"
                    className="sort"
                    onChange={sortSetter}
                >
                    <option value="DESC">DESC</option>
                    <option value="ASC">ASC</option>
                </select>
            </div>:
            <></>
            }
        </div>
    );
}
export default Navbar;
