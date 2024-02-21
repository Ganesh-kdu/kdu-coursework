function Items({
    list,
    setList,
    searchQuery,
}: {
    list: string[];
    setList: Function;
    searchQuery: string;
}) {
    let id = 0;
    const close = (index: number) => {
        setList(list.filter((item, i) => i !== index));
    };
    return (
        <div>
            <h2>Items</h2>
            <>
                {list.map((listItem: string, index: number) => {
                    return listItem.match(searchQuery) ? (
                        <div className="item" id={(id++).toString()}>
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
            </>
        </div>
    );
}
export default Items;
