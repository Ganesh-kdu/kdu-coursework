import IQuote from "../IQuote";
import "./quotes.scss";

function Quotes({quotes, filters, addFilter}:Readonly<{quotes: IQuote[], filters: string[], addFilter: Function}>){
    console.log(quotes)
    return (
        <div></div>
    )
}
export default Quotes;