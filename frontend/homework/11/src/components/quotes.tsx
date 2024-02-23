import IQuote from "../IQuote";
import Quote from "./quote";
import "./quotes.scss";

function Quotes({quotes, filters, addFilter}:Readonly<{quotes: IQuote[], filters: string[], addFilter: Function}>){
    console.log(quotes)
    return (
        <div className="quotes">
            {
                quotes.map((quote:IQuote, index:number) => {
                    const shouldDisplay = quote.tags.some(tag => filters.includes(tag)) || filters.length === 0;

                    return shouldDisplay ? <Quote key={quote._id} quote={quote} addFilter={addFilter}/> : null;
                })
            }
        </div>
    )
}
export default Quotes;