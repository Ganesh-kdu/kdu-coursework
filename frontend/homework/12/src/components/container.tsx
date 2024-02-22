import { createContext, useMemo, useState } from "react";
import "./container.scss";
import Items from "./items";
export const ListContext = createContext<{list:string[], setList:Function}|null>(null);
function MainContainer() {
    const [list, setList] = useState<string[]>([]);
    function add() {
        setList([
            ...list,
            (document.getElementById("newItem") as HTMLInputElement).value,
        ]);
    }
    const listObj = useMemo(() => ({list:list, setList:setList}),[list]);
    return (
        <ListContext.Provider value={listObj}>
            <div className="container">
                <h2>Add Items</h2>
                <input type="text" id="newItem"></input>
                <button onClick={add} className="add">
                    Submit
                </button>
                <Items/>
            </div>
        </ListContext.Provider>
    );
}
export default MainContainer;
