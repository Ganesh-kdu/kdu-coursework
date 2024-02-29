import "./container.scss";
import Items from "./items";

import { useDispatch, useSelector } from "react-redux";
import { addToList, setList, clear } from "../redux/listSlice";
import { RootState } from "../redux/store";
import { useState } from "react";
function MainContainer() {
    const [empty, setEmpty] = useState<boolean>(true);
    const reduxDispatch = useDispatch();
    const list = useSelector((state: RootState) => state.list.list);
    const checked = useSelector((state: RootState) => state.list.checked);
    function removeChecked() {
        reduxDispatch(
            setList(list.filter((_item, index) => !checked.includes(index)))
        );
        reduxDispatch(clear());
    }
    function add() {
        if (
            (document.getElementById("newItem") as HTMLInputElement).value !==
            ""
        ) {
            reduxDispatch(
                addToList(
                    (document.getElementById("newItem") as HTMLInputElement)
                        .value
                )
            );
            (document.getElementById("newItem") as HTMLInputElement).value = "";
        }
    }
    return (
        <div className="container">
            <h2>Add Items</h2>
            <input
                type="text"
                id="newItem"
                onChange={(event: React.ChangeEvent<HTMLInputElement>) => {
                    setEmpty(event.target.value === "");
                }}
            ></input>
            <button onClick={add} className="add" disabled={empty} id="submit">
                Submit
            </button>
            <button onClick={removeChecked} className="add" id="clear">
                Clear Checked
            </button>
            <Items />
        </div>
    );
}
export default MainContainer;
