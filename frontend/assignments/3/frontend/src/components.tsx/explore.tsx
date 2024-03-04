import { createUseStyles } from "react-jss";
import { RootState } from "../redux/store";
import { useDispatch, useSelector } from "react-redux";
import { watchToggle } from "../redux/stockSlice";
import { useState } from "react";
import { Pagination } from "@mui/material";
import { Link } from "react-router-dom";

function Explore() {
    const useStyles = createUseStyles({
        tableContainer: {
            border: "2px solid black",
            borderRadius: "25px",
            margin: "25px",
            justifyItems: "center",
            marginBottom: 0,
            display: "flex",
            flexDirection: "column",
            // alignItems: "center"
        },
        table: {
            borderCollapse: "collapse",
            width: "100%",
        },
        tableHeader: {
            borderBottom: "2px solid black",
            display: "flex",
            padding: "20px",
        },
        row: {
            display: "flex",
            borderBottom: "1px solid gray",
            paddingBottom: "10px",
            boxSizing: "border-box",
            margin: "20px",
        },
        company: {
            background:'none',
            border:'none',
            cursor: 'pointer',
            margin: 0,
            padding: 0,
            fontSize: "16px"
        },
        base: {
            textAlign: "end",
            marginLeft: "auto",
            marginRight: "10px",
        },
        watchlist: {
            width: "10%",
            marginRight: "20px",
            textAlign: "center",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
        },
        tableBody: {},
        bg: {
            backgroundColor: "#2196F3",
            height: "25px",
            width: "25px",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            border: "none",
            borderRadius: "9999px",
        },
        crossBg: {
            backgroundColor: "white",
            height: "25px",
            width: "25px",
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            border: "none",
            borderRadius: "9999px",
        },
        plus: {
            marginTop: "-0.5px",
            color: "white",
            fontSize: "22px",
        },
        check: {
            marginTop: "-0.5px",
            color: "white",
            fontSize: "16px",
        },
        cross: {
            marginTop: "-0.5px",
            color: "red",
            fontSize: "16px",
        },
    });
    const classes = useStyles();
    const { stockList, watchList } = useSelector(
        (state: RootState) => state.stocks
    );
    const reduxDispatch = useDispatch();
    
    const [isHover, setIsHover] = useState<number|null>(null)
    const [page, setPage] = useState<number>(1);
    const handleChange = (_event: React.ChangeEvent<unknown>, value: number) => {
        setPage(value);
      };
    const pageSize = 6;
    return (
        <div className={classes.tableContainer}>
            <div className={classes.tableHeader}>
                <div className={classes.company}>Company</div>
                <div className={classes.base}>Base price</div>
                <div className={classes.watchlist}>Watch List</div>
            </div>
            <div className={classes.tableBody}>
                {stockList.slice(pageSize*(page-1),pageSize*page).map((stock, index) => {
                    return (
                        <div className={classes.row} key={stock.id}>
                            <Link to={stock.id.toString()}>
                            <button className={classes.company}>
                                {stock.stockSymbol}
                            </button>
                            </Link>
                            <div className={classes.base}>
                                {stock.basePrice}
                            </div>
                            <div className={classes.watchlist}>
                                {!watchList[index] ? (
                                    <button
                                        className={classes.bg}
                                        onClick={() =>
                                            reduxDispatch(watchToggle(index))
                                        }
                                        onMouseEnter={() => setIsHover(index)}
                                        onMouseLeave={() => setIsHover(null)}
                                    >
                                        <div
                                            className={
                                                "fa fa-plus-circle" +
                                                " " +
                                                classes.plus
                                            }
                                        ></div>
                                    </button>
                                ) : (
                                    <button
                                        className={isHover==index?classes.crossBg:classes.bg}
                                        onClick={(e:any) => {
                                            e.target.classList.toggle(classes.bg);

                                            reduxDispatch(watchToggle(index));
                                        }}
                                        onMouseEnter={() => setIsHover(index)}
                                        onMouseLeave={() => setIsHover(null)}
                                    >
                                        <div
                                            className={
                                                isHover==index?"fa fa-close" +
                                                " " +
                                                classes.cross:"fa fa-check" +
                                                " " +
                                                classes.check
                                            }
                                        ></div>
                                    </button>
                                )}
                            </div>
                        </div>
                    );
                })}
            </div>
            <Pagination count={Math.ceil(stockList.length/pageSize)} page={page} onChange={handleChange} variant="outlined" color="primary" sx={{p:1.5, margin: "auto", display: { xs: 'none', md: 'block' }}} />
            <Pagination count={Math.ceil(stockList.length/pageSize)} page={page} onChange={handleChange} variant="outlined" color="primary" sx={{p:1.5, margin: "auto", display: { xs: 'block', md: 'none' }}} siblingCount={0} boundaryCount={0}/>

        </div>
    );
}
export default Explore;
