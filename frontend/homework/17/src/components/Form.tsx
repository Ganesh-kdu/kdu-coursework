import { useEffect, useRef } from "react";

export const Form = () => {
    const inputRef = useRef<HTMLInputElement>(null);

    useEffect(() => {
        inputRef.current?.focus();
    }, []);

    return (
        <form>
            <input type="text" ref={inputRef} />
            <br />
            <input type="text" />
            <br />
            <button type="submit">Submit</button>
        </form>
    );
};
