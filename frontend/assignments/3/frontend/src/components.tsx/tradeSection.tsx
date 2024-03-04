import { createUseStyles } from "react-jss";
import { IStock, ITransaction } from "../interfaces/interfaces";
import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../redux/store";
import { addTransaction, updateBalance } from "../redux/stockSlice";

function TradeSection({
    stock,
    newTransaction,
}: Readonly<{ stock: IStock; newTransaction: Function }>) {
    const { balance } = useSelector((state: RootState) => state.stocks);
    const useStyles = createUseStyles({
        details: {
            display: "flex",
            justifyContent: "space-between",
        },
        priceContainer: {},
        input: {
            flexGrow: 1,
            marginLeft:"2px",
            marginRight:"2px"
        },
        graphContainer: {
            width: "100%",
            // flexGrow: 1,
            height: "480px",
            overflow: "scroll",
            position: "relative",
            marginLeft: "10px",
            marginRight: "10px",
            display: "flex",
            maxHeight: "480px",
            alignItems: "flex-end",
            backgroundImage:
                "linear-gradient(rgba(0, 0, 0, 0.4) 1px, transparent 1px),linear-gradient(90deg, rgba(0, 0, 0, 0.4) 1px, transparent 1px)",
            backgroundSize: "100px 125px",
        },
        data: {
            display: "flex",
            paddingBottom: "5px",
            justifyContent: "space-between",
            margin: "10px",
        },
        stockLabel: {
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            paddingLeft: "10px",
            paddingRight: "10px",
            border: "1px solid black",
            fontSize: '20px',
            marginRight:"2px"
        },
        company: { border: "none", paddingRight: "5px", paddingLeft: "10px", fontSize: '20px'},
        stockPrice: {
            display: "flex",
            justifyContent: "center",
            alignItems: "center",
            paddingLeft: "10px",
            paddingRight: "10px",
            border: "1px solid black",
            fontSize: '20px',
            marginLeft:"2px",
            marginRight:"2px"
        },
        price: {
            paddingLeft: "20px",
            paddingRight: "15px",
            fontSize: '20px',
            marginLeft:"4px",
            marginRight:"4px"
        },
        increase: {
            paddingLeft: "3px",
            paddingRight: "3px",
            color: "#2f9e44",
            "&:after": {
                content: '"↑"',
            },
        },
        decrease: {
            paddingLeft: "3px",
            paddingRight: "3px",
            color: "#e03131",
            "&:after": {
                content: '"↓"',
            },
        },
        bar: {
            backgroundColor: "pink",
            width: "18px",
            zIndex: 3,
            // marginTop: 'auto',
            Bottom: "0px",
        },
        change: {
            paddingLeft: "3px",
            paddingRight: "3px",
            paddingTop: "10px",
            fontSize: "15px",
            "&::after": {
                content: '"%"',
            },
        },

        buy: {
            padding: "15px",
            color: "#2f9e44",
            backgroundColor: "#b2f2bb",
            borderColor: "#2f9e44",
            zIndex: "2",
            fontSize: '20px',
            marginLeft:"2px",
            marginRight:"2px",
            border: "1px solid"
        },
        increaseBox: {
            color: "#2f9e44",
            border: "1px solid",
            backgroundColor: "#b2f2bb",
            borderColor: "#2f9e44",
            fontSize: '20px'
        },
        sell: {
            padding: "15px",
            color: "#e03131",
            backgroundColor: "#ffe9e9",
            borderColor: "#e03131",
            fontSize: '20px',
            marginLeft:"2px",
            border: "1px solid"
        },
        decreaseBox: {
            color: "#e03131",
            border: "1px solid",
            backgroundColor: "#ffe9e9",
            borderColor: "#e03131",
            fontSize: '20px'
        },
        symbol: {},
        container: {
            width: "75%",
            flexGrow: 1,
            display: "flex",
            flexDirection: "column",
        },
    });
    const classes = useStyles();
    const reduxDispatch = useDispatch();
    function trade(type: string) {
        const time = Date();
        const price = currentPrice;
        const qty = parseInt(
            (document.getElementById("qty") as HTMLInputElement).value
        );
        (document.getElementById("qty") as HTMLInputElement).value = "0";
        const status =
            (type == "Sell" || qty * price <= balance) && qty > 0
                ? "Success"
                : "Failed";
        const trade: ITransaction = {
            stock_name: stock.stockName,
            stock_symbol: stock.stockSymbol,
            transaction_price: price,
            timestamp: time,
            status: status,
        };
        const displayTrade = {
            qty: qty,
            timestamp: time,
            type: type,
        };
        reduxDispatch(addTransaction(trade));
        if (status == "Success") {
            newTransaction(displayTrade);
            reduxDispatch(
                updateBalance(qty * price * (type == "Sell" ? -1 : 1))
            );
        }
    }
    let [currentPrice, setCurrentPrice] = useState(stock.basePrice);
    let [change, setChange] = useState<boolean>(true);
    let [percentage, setPercentage] = useState(0.0);

    async function createPrices() {
        const diff = -250 + Math.random() * 500;
        setPercentage((100 * diff) / currentPrice);
        setCurrentPrice((previous: number) => previous + diff);
        setChange(diff >= 0);
    }
    useEffect(() => {
        const interval = setInterval(createPrices, 5000);
        return () => clearInterval(interval);
    }, []);
    useEffect(() => {
        let bar = document.createElement("div");
        bar.classList.add(classes.bar);
        if (change) bar.classList.add(classes.increaseBox);
        else bar.classList.add(classes.decreaseBox);
        bar.style.height = currentPrice / 20 + "px";
        document.getElementById("graph")!.appendChild(bar);
    }, [currentPrice]);
    return (
        <div className={classes.container}>
            <div className={classes.data}>
                <div className={classes.stockLabel} id="stock-label">
                    <div className={classes.symbol}>{stock.stockSymbol}</div>
                    <div className={classes.company}>{stock.stockName}</div>
                </div>
                <div className={classes.stockPrice} id="stock-price">
                    <div>Price</div>
                    <div
                        className={
                            classes.price +
                            " " +
                            (change ? classes.increase : classes.decrease)
                        }
                    >
                        {currentPrice.toFixed(2)}
                    </div>
                    <div className={classes.change}>
                        {percentage.toFixed(2)}
                    </div>
                </div>
                <input type="number" placeholder="Enter QTY" id="qty" className={classes.input}/>
                <button
                    className={classes.buy}
                    name="BUY"
                    onClick={() => trade("Buy")}
                >
                    BUY
                </button>
                <button
                    className={classes.sell}
                    name="SELL"
                    onClick={() => trade("Sell")}
                >
                    SELL
                </button>
            </div>
            <div className={classes.graphContainer} id="graph"></div>
        </div>
    );
}
export default TradeSection;
