import "./filters.scss";
function Filters({
    filters,
    setFilters,
}: Readonly<{ filters: string[]; setFilters: Function }>) {
    const remove = (index: number) => {
        setFilters(filters.filter((filter, i) => i !== index));
    };
    return (
        <div className="filters" id="filters">
            Filters
            {filters.map((filter: string, index: number) => {
                return (
                    <div className="filter" key={`${filter}`}>
                        <div className="filter-text">{filter}</div>
                        <button className="remove" onClick={() => remove(index)}>X</button>
                    </div>
                );
            })}
        </div>
    );
}
export default Filters;
