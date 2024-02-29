import { useEffect, useRef } from "react";

export const ScrollToTop = () => {
    const contentRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        contentRef.current!.scrollTop = 0;
    }, []);

    return (
        <div
            ref={contentRef}
            style={{
                height: "200px",
                overflowY: "scroll",
                border: "1px solid black",
            }}
        >
            <p style={{ height: "3000px" }}>
                This is a long content. Scroll down and refresh the page, you'll
                be scrolled to top.
            </p>
        </div>
    );
};
