import { useEffect, useRef } from "react";

export const Timer = () => {
    const timerRef = useRef<HTMLSpanElement>(null);

    useEffect(() => {
        let timer = setInterval(() => {
            timerRef.current!.innerText = (
                parseInt(timerRef.current!.innerText) + 1
            ).toString();
        }, 1000);

        return () => clearInterval(timer);
    }, []);

    return (
        <div>
            Timer: <span ref={timerRef}>0</span> seconds
        </div>
    );
};
