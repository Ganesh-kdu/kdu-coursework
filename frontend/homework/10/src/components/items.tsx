import "./container.scss";
function Items({
    list,
    setList,
    searchQuery,
}: Readonly<{
    list: string[];
    setList: Function;
    searchQuery: string;
}>) {
    const close = (index: number) => {
        setList(list.filter((item, i) => i !== index));
    };
    let id = 0;
    return (
        <div>
            <h2>Items</h2>
            <div id="list">
                {list.map((listItem: string, index: number) => {
                    return RegExp(searchQuery).exec(listItem) ? (
                        <div
                            className={`item ${id != 0 ? "" : "first-item"}`}
                            id={(id++).toString()}
                        >
                            <div>{listItem}</div>
                            <button
                                className="close"
                                onClick={() => close(index)}
                            >
                                X
                            </button>
                        </div>
                    ) : (
                        ""
                    );
                })}
            </div>
        </div>
    );
}
export default Items;
