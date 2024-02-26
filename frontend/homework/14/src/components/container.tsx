import "./container.scss";
import Items from "./items";

import { useDispatch } from "react-redux";
import { addToList } from "../redux/listSlice";
function MainContainer() {
    const reduxDispatch = useDispatch();

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
            <input type="text" id="newItem"></input>
            <button onClick={add} className="add">
                Submit
            </button>
            <Items />
        </div>
    );
}
export default MainContainer;
