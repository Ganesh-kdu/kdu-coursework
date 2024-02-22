import { useState } from "react";
import "./container.scss";
import Items from "./items";
function MainContainer({ query }: any) {
    console.log(query);
    const [list, setList] = useState<string[]>([]);
    function add() {
        setList([
            ...list,
            (document.getElementById("newItem") as HTMLInputElement).value,
        ]);
    }
    return (
        <div className="container">
            <h2>Add Items</h2>
            <input type="text" id="newItem"></input>
            <button onClick={add} className="add">
                Submit
            </button>
            <Items list={list} setList={setList} searchQuery={query} />
        </div>
    );
}
export default MainContainer;
