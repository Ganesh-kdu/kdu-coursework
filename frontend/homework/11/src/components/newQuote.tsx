import { useState } from "react";
import "./newQuote.scss";

function NewQuote({ addQuote }: Readonly<{ addQuote: Function }>) {
    const [loading, setLoading] = useState<boolean>(false);
    const getData = async () => {
        setLoading(true);
        const response = await fetch(
            "https://api.quotable.io/quotes/random?limit=1"
        );
        const result = await response.json();
        await addQuote((prev: string[]) => [...prev, ...result]);
        setLoading(false);
    };
    return (
        <>
            { loading ? <div className="spinner"/> : null}
            <button
                onClick={getData}
                className="quote-button"
                id="quote-button"
                disabled={loading}
            >
                NEW QUOTE
            </button>
        </>
    );
}
export default NewQuote;
