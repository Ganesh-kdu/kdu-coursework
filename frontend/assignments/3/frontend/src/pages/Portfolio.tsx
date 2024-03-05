import { useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { useState } from "react";
import { createUseStyles } from "react-jss";
import { ITransaction } from "../interfaces/interfaces";

function Portfolio() {
    const { trades } = useSelector((state: RootState) => state.stocks);
    const [textFilter, setTextFilter] = useState<string>("");
    const [startDateFilter, setStartDateFilter] = useState<string>("");
    const [endDateFilter, setEndDateFilter] = useState<string>("");
    const [passedFilter, setPassedFilter] = useState<boolean>(false);
    const [failedFilter, setFailedFilter] = useState<boolean>(false);
    const [selectedStocks, setSelectedStocks] = useState<string[]>([]);
    console.log(trades[0])
    interface GroupedTransactions {
        [date: string]: ITransaction[];
    }
    const filteredTransactions = trades
        .filter((transaction) => {
            return (
                transaction.stock_name
                    .toLowerCase()
                    .includes(textFilter.toLowerCase()) ||
                transaction.stock_symbol
                    .toLowerCase()
                    .includes(textFilter.toLowerCase())
            );
        })
        .filter((transaction) => {
            if (startDateFilter) {
                return (
                    new Date(transaction.timestamp) >= new Date(startDateFilter)
                );
            }
            return true;
        })
        .filter((transaction) => {
            if (endDateFilter) {
                return (
                    new Date(transaction.timestamp) <= new Date(endDateFilter)
                );
            }
            return true;
        })
        .filter((transaction) => {
            if (passedFilter && transaction.status !== "Passed") {
                return false;
            }
            if (failedFilter && transaction.status !== "Failed") {
                return false;
            }
            return true;
        });
    const groupTransactionsByDate = (transactions: ITransaction[]) => {
        transactions.sort((a,b)=>{return (new Date(b.timestamp)).getTime() - (new Date(a.timestamp)).getTime()})
        const groupedTransactions: GroupedTransactions = {};
        transactions.forEach((transaction) => {
            const date = new Date(transaction.timestamp).toLocaleDateString(
                "en-US",
                {
                    day: "2-digit",
                    month: "short",
                    year: "numeric",
                }
            );
            if (!groupedTransactions[date]) {
                groupedTransactions[date] = [];
            }
            groupedTransactions[date].push(transaction);
        });
        return groupedTransactions;
    };
    let displayList = groupTransactionsByDate(filteredTransactions);
    const classes = createUseStyles({
        dot: {
            width:'10px',
            height:'10px',
            borderRadius: '9999px',
            margin:'4px',
            marginRight:'10px'
        },
        main_port_container: {
            display: "flex",
            width: "90vw",
            height: "80vh",
            marginLeft: "5rem",
            flexWrap: "wrap",
            marginTop: "1rem",
            "@media (max-width: 750px)": {
                margin: "1rem",
            },
        },
        filter_box_container: {
            margin: "0.5rem",
            minWidth: "22vw",
            maxWidth: "auto",
            alignSelf: "center",
            height: "50vh",
            display: "flex",
            flex: "1",
            flexDirection: "column",
            border: "2px solid #000",
            borderRadius: "15px",
            backgroundColor: "#e9ecef",

            "@media (max-width: 750px)": {
                margin: "0rem",
            },
        },
        results_showing_container: {
            margin: "0.5rem",
            minWidth: "65vw",
            height: "75vh",
            overflowY: "auto",
            "&:-webkit-scrollbar": {
                display: "none",
            },
        },
        one_filter_container: {
            display: "flex",
            justifyContent: "space-between",
            padding: "1rem",
            borderBottom: "2px solid #6f7072",
            fontSize: "1.5rem",
            fontWeight: "500",
        },
        fw: {
            width:'23%',
            textAlign:'end'
        },
        two_filter_container: {
            display: "flex",
            justifyContent: "center",
            marginTop: "0.5rem",
            padding: "0.7rem",
            borderBottom: "2px solid #6f7072",

            "& input": {
                height: "3.5vh",
                width: "20vw",
                borderRadius: "10px",
                border: "2px solid #6f7072",
                fontSize: "1rem",
                display: "flex",
                flex: "1",
            },
        },
        third_filter_container: {
            display: "flex",
            justifyContent: "space-between",
            borderBottom: "2px solid #6f7072",
        },
        fw2:{
            width: '25%'
        },
        dateBox: {
            margin: "1rem",
            padding: "0.2rem",
            width: "8vw",
            height: "4vh",
            borderRadius: "10px",
            border: "2px solid #6f7072",
            display: "flex",
            flex: "1",
        },
        four_filter_container: {
            display: "flex",
            flexDirection: "column",
            padding: "1rem",
            borderBottom: "2px solid #6f7072",
        },
        clear_button: {
            border: "none",
            color: "#1971c2",
            fontSize: "1rem",
            fontWeight: "600",
        },
        five_filter_container: {
            display: "flex",
            flexDirection: "column",
            overflowY: "auto",
            "&::-webkit-scrollbar": {
                display: "none",
            },
            padding: "1rem",
        },
        transactionSet: {marginTop: '40px'},
        transactionDate: {position:'sticky',top:'0px',borderBottom:'1px dashed #6f7072', backgroundColor:'white', paddingBottom:'5px', color: '#6f7072'},
        transactionContainer: {display: "flex", justifyContent: 'space-between', borderBottom:'1px solid black',paddingTop:'15px',paddingBottom:'10px'},
    })();
    const clearAllFilters = () => {
        setTextFilter("");
        setStartDateFilter("");
        setEndDateFilter("");
        setPassedFilter(false);
        setFailedFilter(false);
        setSelectedStocks([]);
    };
    const stockNames = Array.from(
        new Set(trades.map((trade) => trade.stock_name))
    );
    const handleStockFilterChange = (stockName: string) => {
        if (selectedStocks.includes(stockName)) {
          setSelectedStocks(selectedStocks.filter((stock) => stock !== stockName));
        } else {
          setSelectedStocks([...selectedStocks, stockName]);
        }
      };
    return (
        <div className={classes.main_port_container}>
            <div className={classes.filter_box_container}>
                <div className={classes.one_filter_container}>
                    <span>Filters</span>
                    <button
                        className={classes.clear_button}
                        onClick={clearAllFilters}
                    >
                        Clear All
                    </button>
                </div>
                <div className={classes.two_filter_container}>
                    <input
                        type="text"
                        placeholder="Search For a Stock"
                        value={textFilter}
                        onChange={(e) => setTextFilter(e.target.value)}
                    />
                </div>
                <div className={classes.third_filter_container}>
                    <input
                        type="date"
                        placeholder="Start Date"
                        className={classes.dateBox}
                        value={startDateFilter}
                        onChange={(e) => setStartDateFilter(e.target.value)}
                    />
                    <input
                        type="date"
                        placeholder="End Date"
                        className={classes.dateBox}
                        value={endDateFilter}
                        onChange={(e) => setEndDateFilter(e.target.value)}
                    />
                </div>
                <div className={classes.four_filter_container}>
                    <label style={{ marginBottom: "0.3rem" }}>
                        <input
                            type="checkbox"
                            checked={passedFilter}
                            onChange={() => setPassedFilter(!passedFilter)}
                            style={{ marginRight: "1rem" }}
                        />Passed
                    </label>
                    <label style={{ marginBottom: "0.3rem" }}>
                        <input
                            type="checkbox"
                            checked={failedFilter}
                            onChange={() => setFailedFilter(!failedFilter)}
                            style={{ marginRight: "1rem" }}
                        />Failed
                    </label>
                </div>
                <div className={classes.five_filter_container}>
                    {stockNames.map((stockName) => (
                        <label key={stockName}>
                            <input
                                type="checkbox"
                                checked={selectedStocks.includes(stockName)}
                                onChange={() =>
                                    handleStockFilterChange(stockName)
                                }
                            />
                            {stockName}
                        </label>
                    ))}
                </div>
            </div>
            <div className={classes.results_showing_container}>
                {Object.entries(displayList).map(([date, transactions]) => {
                    return (
                        <div key={date} className={classes.transactionSet}>
                            <div className={classes.transactionDate}>
                                {date}
                            </div>
                            {transactions.map((transaction) => {
                                return (
                                    <div
                                        key={transaction.timestamp}
                                        className={classes.transactionContainer}
                                        style={{
                                            opacity:
                                              selectedStocks.length > 0 &&
                                              !selectedStocks.includes(transaction.stock_name)
                                                ? 0.5
                                                : 1,
                                          }}
                                    >
                                        <div className={classes.fw2}>{transaction.stock_name}</div>
                                        <div className={classes.fw}>{transaction.stock_symbol}</div>
                                        <div className={classes.fw}>{transaction.transaction_price}</div>
                                        <div className={classes.fw}>{transaction.timestamp.split('T')[1].split(':')[0] + ':' + transaction.timestamp.split('T')[1].split(':')[1]}</div>
                                        <div className={classes.dot} style={transaction.status=="Failed"?{backgroundColor:'red'}:{backgroundColor:'green'}}></div>
                                    </div>
                                );
                            })}
                        </div>
                    );
                })}
            </div>
        </div>
    );
}
export default Portfolio;
