import { createUseStyles } from "react-jss";
import { IDisplayTransaction, ILiveTrade } from "../interfaces/interfaces";

function HistorySection({
    trades,
    liveTrades,
}: Readonly<{ trades: IDisplayTransaction[]; liveTrades: ILiveTrade[] }>) {
    const classes = createUseStyles({
        container: { width: "25%", display: "flex", flexDirection: "column" },
        commonStyle: {
            border: "1px solid black",
            flexGrow: 1,
            width: "",
            margin: "10px",
            marginLeft: "20px",
        },
        transactionContainer: {
            border: "1px solid black",
            borderRadius: "20px",
            margin: "10px",
            display: "flex",
            justifyContent: "space-between",
            padding: "10px",
        },
        buy: {
            color: "green",
        },
        sell: {
            color: "red",
        },
        left: {},
        right: {},
        timeStamp: {
            fontSize: "10px",
        },
        historyDiv: {
            padding: "10px",
        },
    })();

    return (
        <div className={classes.container}>
            <div className={classes.commonStyle}>
                <div className={classes.historyDiv}>History</div>
                {trades.map((trade: IDisplayTransaction) => {
                    return (
                        <div
                            className={classes.transactionContainer}
                            key={trade.timestamp}
                        >
                            <div className={classes.left}>
                                <div>{trade.qty}</div>
                                <div className={classes.timeStamp}>
                                    {trade.timestamp.split("(")[0]}
                                </div>
                            </div>
                            <div
                                className={
                                    classes.right +
                                    " " +
                                    (trade.type == "Buy"
                                        ? classes.buy
                                        : classes.sell)
                                }
                            >
                                {trade.type}
                            </div>
                        </div>
                    );
                })}
            </div>
            <div className={classes.commonStyle}>
                {liveTrades.map((trade: ILiveTrade) => {
                    return (
                        <div
                            className={classes.transactionContainer}
                            key={trade.name + trade.qty}
                        >
                            <div>
                                Someone {trade.type} {trade.qty} {trade.name}
                            </div>
                        </div>
                    );
                })}
            </div>
        </div>
    );
}
export default HistorySection;
