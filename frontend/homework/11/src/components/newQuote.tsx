import { Dispatch, SetStateAction } from "react";
import IQuote from "../IQuote";

function NewQuote({addQuote}:{addQuote: Function}){
    const getData = async () => {
        const response = await fetch(
            "https://api.quotable.io/quotes/random?limit=1"
        );
        const result = await response.json();
        await addQuote((prev:string[]) => [...prev,...result]);
    };
    return (
        <button onClick={getData}></button>
    )
}
export default NewQuote;