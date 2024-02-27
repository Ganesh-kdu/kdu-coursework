import "./container.scss";
import Items from "./items";

import { useDispatch, useSelector } from "react-redux";
import { addToList, setList, clear } from "../redux/listSlice";
import { RootState } from "../redux/store";
function MainContainer() {
    const reduxDispatch = useDispatch();
    const list = useSelector((state: RootState) => state.list.list);
    const checked = useSelector((state: RootState) => state.list.checked);
    function removeChecked(){
        reduxDispatch(setList(list.filter((item, index) => !checked.includes(index))))
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
    console.log(list);
    console.log(checked);
    return (
        <div className="container">
            <h2>Add Items</h2>
            <input type="text" id="newItem"></input>
            <button onClick={add} className="add">
                Submit
            </button>
            <button onClick={removeChecked} className="add">
                Clear Checked
            </button>
            <Items />
        </div>
    );
}
export default MainContainer;
