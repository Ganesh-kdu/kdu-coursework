function Filters({f}:any ){
    console.log(f)
    return (
        <div>Filters: {f.map((filter: string) => {
            return <div>{filter}</div>
        })}</div>
    )
}
export default Filters;