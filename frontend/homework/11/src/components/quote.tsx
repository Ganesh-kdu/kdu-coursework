import IQuote from "../IQuote";
import "./quotes.scss";
function Quote({quote, addFilter}:Readonly<{quote: IQuote, addFilter:Function}>){
    return (
        <div className="quote-container">
            <div className="quote">{quote.content}</div>
            <div className="author">~{quote.author}</div>
            <div className="date">{quote.dateAdded}</div>
            <div className="tags">
                {
                    quote.tags.map((tag:string, index:number) => {
                        return <button className="tag" key={tag} onClick={() => addFilter((prev:Set<string>) => new Set([...Array.from(prev), tag]))}>{tag}</button>
                    })
                }
            </div>
        </div>
    )
}
export default Quote;