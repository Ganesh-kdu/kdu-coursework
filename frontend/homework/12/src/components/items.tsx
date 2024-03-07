import { useContext } from "react";
import "./container.scss";
import { ListContext } from "./container";
import { SearchContext } from "../App";
function Items() {
    const listContext = useContext(ListContext);
    const { list, setList } = listContext as { list: string[]; setList: (list: string[]) => void };
    const close = (index: number) => {
        setList(list.filter((item, i) => i !== index));
    };
    const searchContext = useContext(SearchContext);
    const { searchQuery } = searchContext as {
        searchQuery: string;
        setQuery: (searchQuery: string) => void;
    };
    let id = 0;
    return (
        <div>
            <h2>Items</h2>
            <div id="list">
                {list.map((listItem: string, index: number) => {
                    return RegExp(searchQuery).exec(listItem) ? (
                        <div
                            className={`item ${id != 0 ? "" : "first-item"}`}
                            id={(id++).toString()}
                        >
                            <div>{listItem}</div>
                            <button
                                className="close"
                                onClick={() => close(index)}
                            >
                                X
                            </button>
                        </div>
                    ) : (
                        ""
                    );
                })}
            </div>
        </div>
    );
}
export default Items;
