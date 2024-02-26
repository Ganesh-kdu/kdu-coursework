import "./container.scss";

import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { setList, check } from "../redux/listSlice";

function Items() {
    const reduxDispatch = useDispatch();
    const list = useSelector((state: RootState) => state.list.list);
    const searchQuery = useSelector(
        (state: RootState) => state.search.searchQuery
    );

    const close = (index: number) => {
        reduxDispatch(setList(list.filter((item, i) => i !== index)));
    };

    let id = 0;
    return (
        <div>
            <h2>Items</h2>
            <div id="list">
                {list.map((listItem: string, index: number) => {
                    return RegExp(searchQuery).exec(listItem) ? (
                        <div
                            className={`item ${id !== 0 ? "" : "first-item"}`}
                            id={(id++).toString()}
                            key={id+listItem}
                        >
                            <div>
                                <input
                                    type="checkbox"
                                    onChange={() => reduxDispatch(check(index))}
                                />
                                {listItem}
                            </div>
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
