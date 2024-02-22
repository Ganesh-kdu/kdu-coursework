import "./newQuote.scss"
function NewQuote({addQuote}:Readonly<{addQuote: Function}>){
    const getData = async () => {
        const response = await fetch(
            "https://api.quotable.io/quotes/random?limit=1"
        );
        const result = await response.json();
        await addQuote((prev:string[]) => [...prev,...result]);
    };
    return (
        <button onClick={getData} className="quote-button">NEW QUOTE</button>
    )
}
export default NewQuote;